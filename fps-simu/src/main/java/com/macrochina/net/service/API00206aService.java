package com.macrochina.net.service;

import com.alibaba.fastjson.JSONObject;
import com.macrochina.net.dao.API00206aRepository;
import com.macrochina.net.dto.API00206aDto;
import com.macrochina.net.entity.*;
import com.macrochina.net.util.HttpClientUtils;
import com.macrochina.net.util.MessageUtils;
import com.macrochina.net.utils.SysParamsContst;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.utils.DateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.*;

@Service
public class API00206aService {

    @Autowired
    private API00206aRepository api00206aRepository;

    @Autowired
    private FpsParamService fpsParamService;

    @Autowired
    private MessageService messageService;

    public void save(API00206a api00206a) {
        api00206aRepository.save(api00206a);
    }

    public API00206a findOneById(int id) {
        return api00206aRepository.findById(id).get();
    }

    public void deleteById(int id) {
        api00206aRepository.deleteById(id);
    }

    public Page<API00206a> findAll(Integer page, Integer size, API00206a api00206a) {
        Pageable pageable = PageRequest.of(page == 0 ? 0 : page - 1, size == 0 ? 10 : size);
        Specification<API00206a> specification = (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> list = new ArrayList<>();
            if (StringUtils.isNotBlank(api00206a.getRegnTp())) {
                list.add(criteriaBuilder.like(root.get("RegnTp"), "%" + api00206a.getRegnTp() + "%"));
            }
            if (StringUtils.isNotBlank(api00206a.getOrgName())) {
                list.add(criteriaBuilder.like(root.get("OrgName"), "%" + api00206a.getOrgName() + "%"));
            }
            if (StringUtils.isNotBlank(api00206a.getProxyId())) {
                list.add(criteriaBuilder.like(root.get("ProxyId"), "%" + api00206a.getProxyId() + "%"));
            }
            if (StringUtils.isNotBlank(api00206a.getProxyType())) {
                list.add(criteriaBuilder.like(root.get("ProxyType"), "%" + api00206a.getProxyType() + "%"));
            }
            if (StringUtils.isNotBlank(api00206a.getRegnDt())) {
                list.add(criteriaBuilder.like(root.get("RegnDt"), "%" + api00206a.getRegnDt() + "%"));
            }
            return criteriaBuilder.and(list.toArray(new Predicate[0]));
        };
        return api00206aRepository.findAll(specification, pageable);
    }

    @Async("asyncTaskExecutor")
    public void sendAPI00206a(API00206a api00206a) {
        FpsParam param = fpsParamService.findById();
        String url = param.getHostAdr() + "/proxy/G3/CAS/PayNowMaintenance";
        Map mp = new HashMap<>();
        mp.put("Content-type", "application/json");
        mp.put("ClientId","MBANK");
        mp.put("CTxnId", MessageUtils.getCTxnId(param.getSendBank()));
        mp.put("CDateTime", DateUtils.formatDate(new Date(), "yyyymmddhhmmss"));
        API00206aDto dto = new API00206aDto();
        BeanUtils.copyProperties(api00206a,dto);
        Org org = new Org();
        org.setTpOfOrg(api00206a.getTpOfOrg());
        org.setOrgName(api00206a.getOrgName());
        org.setRegnDt(api00206a.getRegnDt());
        dto.setOrg(org);
        IndvPrsn indvPrsn = new IndvPrsn();
        indvPrsn.setGvnNm(api00206a.getGvnNm());
        indvPrsn.setMddlNm(api00206a.getMddlNm());
        indvPrsn.setLastNm(api00206a.getLastNm());
        dto.setIndvPrsn(indvPrsn);
        String json = JSONObject.toJSONString(dto);
        Message message = new Message();
        message.setTag("OpfGateway");
        message.setMsgId(MessageUtils.genMsgId());
        message.setMsgDef("PayNowMaintenance");
        message.setDirct(Integer.parseInt(SysParamsContst.OUTWARD));
        message.setRemark(json);
        messageService.save(message);
        HttpClientUtils.doPostHeader(url,json,mp);
    }
}
