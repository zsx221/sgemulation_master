package com.macrochina.net.service;

import com.alibaba.fastjson.JSONObject;
import com.macrochina.net.dao.API00206cRepository;
import com.macrochina.net.entity.API00206c;
import com.macrochina.net.entity.FpsParam;
import com.macrochina.net.entity.Message;
import com.macrochina.net.util.HttpClientUtils;
import com.macrochina.net.util.MessageUtils;
import com.macrochina.net.utils.SysParamsContst;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.util.*;

@Service
public class API00206cService {

    @Autowired
    private API00206cRepository api00206cRepository;

    @Autowired
    private FpsParamService fpsParamService;

    @Autowired
    private MessageService messageService;

    public void save(API00206c api00206c) {
        api00206cRepository.save(api00206c);
    }

    public API00206c findOneById(int id) {
        return api00206cRepository.findById(id).get();
    }

    public void deleteById(int id) {
        api00206cRepository.deleteById(id);
    }

    public Page<API00206c> findAll(Integer page, Integer size, API00206c api00206c) {
        Pageable pageable = PageRequest.of(page == 0 ? 0 : page - 1, size == 0 ? 10 : size);
        Specification<API00206c> specification = (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> list = new ArrayList<>();
            if (StringUtils.isNotBlank(api00206c.getEnquiryChoice())) {
                list.add(criteriaBuilder.like(root.get("EnquiryChoice"), "%" + api00206c.getEnquiryChoice() + "%"));
            }
            if (StringUtils.isNotBlank(api00206c.getProxyId())) {
                Path<String> ProxyId = root.get("ProxyId");
                list.add(criteriaBuilder.like(ProxyId, "%" + api00206c.getProxyId() + "%"));
            }
            if (StringUtils.isNotBlank(api00206c.getProxyType())) {
                Path<String> ProxyType = root.get("ProxyType");
                list.add(criteriaBuilder.like(ProxyType, "%" + api00206c.getProxyType() + "%"));
            }
            return criteriaBuilder.and(list.toArray(new Predicate[0]));
        };
        return api00206cRepository.findAll(specification, pageable);
    }

    @Async("asyncTaskExecutor")
    public void sendAPI00206c(API00206c api00206c) {
        FpsParam param = fpsParamService.findById();
        String url = param.getHostAdr() + "/proxy/G3/CAS/PayNowEnquiry";
        Map mp = new HashMap<>();
        mp.put("Content-type", "application/json");
        mp.put("ClientId","MBANK");
        mp.put("CTxnId", MessageUtils.getCTxnId(param.getSendBank()));
        mp.put("CDateTime", DateUtils.formatDate(new Date(), "yyyymmddhhmmss"));
        String json = JSONObject.toJSONString(api00206c);
        Message message = new Message();
        message.setTag("OpfGateway");
        message.setMsgId(MessageUtils.genMsgId());
        message.setMsgDef("PayNowEnquiry");
        message.setDirct(Integer.parseInt(SysParamsContst.OUTWARD));
        message.setRemark(json);
        messageService.save(message);
        HttpClientUtils.doPostHeader(url,json,mp);
    }
}
