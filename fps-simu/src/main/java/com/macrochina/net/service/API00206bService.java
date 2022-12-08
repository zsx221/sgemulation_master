package com.macrochina.net.service;

import com.alibaba.fastjson.JSONObject;
import com.macrochina.net.dao.API00206bRepository;
import com.macrochina.net.entity.API00206a;
import com.macrochina.net.entity.API00206b;
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

import javax.persistence.criteria.Predicate;
import java.util.*;

@Service
public class API00206bService {
    
    @Autowired
    private API00206bRepository api00206bRepository;

    @Autowired
    private FpsParamService fpsParamService;

    @Autowired
    private MessageService messageService;

    public void save(API00206b api00206b) {
        api00206bRepository.save(api00206b);
    }

    public API00206b findOneById(int id) {
        return api00206bRepository.findById(id).get();
    }

    public void deleteById(int id) {
        api00206bRepository.deleteById(id);
    }

    public Page<API00206b> findAll(Integer page, Integer size, API00206b api00206b) {
        Pageable pageable = PageRequest.of(page == 0 ? 0 : page - 1, size == 0 ? 10 : size);
        Specification<API00206a> specification = (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> list = new ArrayList<>();
            if (StringUtils.isNotBlank(api00206b.getProxyId())) {
                list.add(criteriaBuilder.like(root.get("ProxyId"), "%" + api00206b.getProxyId() + "%"));
            }
            if (StringUtils.isNotBlank(api00206b.getProxyType())) {
                list.add(criteriaBuilder.like(root.get("ProxyType"), "%" + api00206b.getProxyType() + "%"));
            }
            return criteriaBuilder.and(list.toArray(new Predicate[0]));
        };
        return api00206bRepository.findAll(specification, pageable);
    }

    @Async("asyncTaskExecutor")
    public void sendAPI00206b(API00206b api00206b) {
        FpsParam param = fpsParamService.findById();
        String url = param.getHostAdr() + "/proxy/G3/CAS/PayNowLookUp";
        Map mp = new HashMap<>();
        mp.put("Content-type", "application/json");
        mp.put("ClientId","MBANK");
        mp.put("CTxnId", MessageUtils.getCTxnId(param.getSendBank()));
        mp.put("CDateTime", DateUtils.formatDate(new Date(), "yyyymmddhhmmss"));
        String json = JSONObject.toJSONString(api00206b);
        Message message = new Message();
        message.setTag("OpfGateway");
        message.setMsgId(MessageUtils.genMsgId());
        message.setMsgDef("PayNowLookUp");
        message.setDirct(Integer.parseInt(SysParamsContst.OUTWARD));
        message.setRemark(json);
        messageService.save(message);
        HttpClientUtils.doPostHeader(url,json,mp);
    }
}
