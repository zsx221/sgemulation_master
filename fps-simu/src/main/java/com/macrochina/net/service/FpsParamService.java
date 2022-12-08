package com.macrochina.net.service;

import com.macrochina.net.dao.FpsParamRepository;
import com.macrochina.net.entity.FpsParam;
import com.macrochina.net.util.Constants;
import com.macrochina.net.util.NewXmlSignUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class FpsParamService {

    @Autowired
    FpsParamRepository fpsParamRepository;

    @Autowired
    FpsParamService fpsParamService;

    public List<FpsParam> findAll(){
        return fpsParamRepository.findAll();
    }

//    public FpsParam findOneByBizType(String bizType){
//        return fpsParamRepository.findOneByBizType(bizType);
//    }

    public FpsParam findById(){
        List<FpsParam> list = fpsParamRepository.findAll();
        if (!CollectionUtils.isEmpty(list)) {
            return list.get(0);
        }else {
            return null;
        }
    }

    public void save(FpsParam fpsParam){
        fpsParamRepository.save(fpsParam);
    }

    public  String  xmlStringSign(String xml){
        return "0".equals(this.findById().getIsSign()) ? xml : NewXmlSignUtil.xmlStringSign(xml, Constants.OFFORON,"admin", "123456", "", "", "");

    }
}