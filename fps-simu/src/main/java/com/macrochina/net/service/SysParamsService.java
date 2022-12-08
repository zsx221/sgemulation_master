package com.macrochina.net.service;

import com.macrochina.net.config.SpringUtil;
import com.macrochina.net.dao.SysParamsRepository;
import com.macrochina.net.entity.SysParams;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SysParamsService {

    private static Log logger=LogFactory.getLog(SysParamsService.class);

    @Autowired
    private SysParamsRepository sysParamsRepository;

    public  static Map<String,String> sysParams=new HashMap<>();


    public static void SysParamsServiceInit(){
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        sysParams=findAllSysParams();
    }

    /**
     *获取参数信息（先从内存，再从DB）
     * @param sysCode
     * @return
     */
    public static String getSysParams(String sysCode){
        String rtn=null;

        if(StringUtils.isEmpty(sysCode)){
            return rtn;
        }
        rtn=sysParams.get(sysCode);
        if(rtn==null){
            sysParams= SpringUtil.getBean(SysParamsService.class).findAllSysParams();
            rtn=sysParams.get(sysCode);
            if(rtn==null){
                logger.error("查询系统参数错误!sysCode:"+sysCode);
            }
        }
        return  rtn;
    }

    /**
     * 加载所有系统参数
     */
    public static   Map<String ,String > findAllSysParams(){
        Map<String,String> rtn=new HashMap<>();
        List<SysParams> ls=SpringUtil.getBean(SysParamsRepository.class).findAll();
        if(ls!=null&ls.size()>0){
            for (SysParams sysParams:ls) {
                rtn.put(sysParams.getSysCode(), sysParams.getSysValue());
            }
        }
        return  rtn;
    }

    /**
     *根据code查询系统参数
     */
    public SysParams findSysParams(String sysCode){
        SysParams rtn=null;
        if(StringUtils.isEmpty(sysCode)){
            return rtn;
        }
        rtn=this.sysParamsRepository.findBySysCode(sysCode);
        if(rtn==null){
            logger.error("查询系统参数错误!sysCode:"+sysCode);
        }
        return  rtn;
    }

    /**
     * 查询所有参数信息
     */
    public Page<SysParams> findAllSysParamsByCdn(int page, int size, SysParams sysParams){
        Page<SysParams> rtn=null;
        Pageable pageable = PageRequest.of(page, size);
        Specification<SysParams> sps=new Specification<SysParams>() {
            @Override
            public Predicate toPredicate(Root<SysParams> root, CriteriaQuery<?> query,
                                         CriteriaBuilder criteriaBuilder) {
                Predicate predicate=null;
                if(!StringUtils.isEmpty(sysParams.getSysName())){
                    Path<String> sysName = root.get("sysName");
                    predicate=criteriaBuilder.like(sysName, "%"+sysParams.getSysName()+"%");
                }
                if(!StringUtils.isEmpty(sysParams.getSysCode())){
                    Path<String> sysCode = root.get("sysCode");
                    if(predicate!=null){
                        predicate= criteriaBuilder.and(criteriaBuilder.like(sysCode, "%"+sysParams.getSysCode()+"%"));
                    }
                }
                if(!StringUtils.isEmpty(sysParams.getSysValue())){
                    Path<String> sysValue = root.get("sysValue");
                    if(predicate!=null){
                        predicate= criteriaBuilder.and(criteriaBuilder.like(sysValue, "%"+sysParams.getSysValue()+"%"));
                    }
                }
                query.where(predicate);
                return query.getRestriction();
            }
        };
        rtn=sysParamsRepository.findAll(sps, pageable);
        return rtn;
    }
}
