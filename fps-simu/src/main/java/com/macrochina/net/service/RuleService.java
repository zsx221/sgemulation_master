package com.macrochina.net.service;

import com.macrochina.net.dao.BizRuleDataRepository;
import com.macrochina.net.dao.BizRuleSetRepository;
import com.macrochina.net.entity.BizRuleData;
import com.macrochina.net.entity.BizRuleSet;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@Service
public class RuleService {//200

    private static Log logger = LogFactory.getLog(SysParamsService.class);

    @Autowired
    private BizRuleDataRepository bizRuleDataRepository;

    @Autowired
    private BizRuleSetRepository bizRuleSetRepository;

    /*    public RuleService(BizRuleDataRepository bizRuleDataRepository,BizRuleSetRepository bizRuleSetRepository){
            this.bizRuleDataRepository = bizRuleDataRepository;
            this.bizRuleSetRepository = bizRuleSetRepository;
        }*/
    // 把一个字符串的第一个字母大写、效率是最高的、
    private static String getMethodName(String fildeName) {
        fildeName = fildeName.substring(0, 1).toUpperCase()+fildeName.substring(1);
        return fildeName;
    }

    /**
     * @return boolean
     * @Author fangyu
     * @Description 检测是否命中规则
     * @Date 2021/8/18 09:35
     * @Param [targetXmlEntity 需要检测的对象, bizRuleData规则]
     **/
    private static boolean ruleOption(Object targetXmlEntity, BizRuleData bizRuleData) {
        if (targetXmlEntity.getClass().getName().equals(bizRuleData.getClassName())) {
            String value = getValue(targetXmlEntity, bizRuleData.getField());
            return bizRuleData.getClassValue().equals(value);
        }
        return false;
    }

    /**
     * @return java.lang.String
     * @Author fangyu
     * @Description 获取具体属性
     * @Date 2021/8/20 09:29
     * @Param [targetXmlEntity, fieldName]
     **/
    public static String getValue(Object targetXmlEntity, String fieldName) {
        if (targetXmlEntity == null) {
            return null;
        }
        try {
            int index = fieldName.indexOf(".");
            if (index == 0) {
                fieldName = fieldName.substring(1);
                index = fieldName.indexOf(".");
            }
            String srcField = "";
            String endField = "";
            if (index < 0) {
                srcField = fieldName;
            } else {
                srcField = fieldName.substring(0, index);
                endField = fieldName.substring(index);
            }
            Object next = null;
            Field f = null;
            f = targetXmlEntity.getClass().getDeclaredField(srcField);
            f.setAccessible(true);
            if (index < 1) {
                Method m = null;
                m = targetXmlEntity.getClass().getMethod("get" + getMethodName(f.getName()));
                m.setAccessible(true);
                //已经到具体的属性了
                return String.valueOf(m.invoke(targetXmlEntity));
            } else {
                Object obj = f.get(targetXmlEntity);
                if (obj instanceof List) {
                    return getValue(((List) obj).get(0), endField);
                }
                return getValue(obj, endField);

            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * @return com.macrochina.net.entity.BizRuleSet
     * @Author fangyu
     * @Description 返回命中的规则
     * @Date 2021/8/20 09:27
     * @Param [bizMsgDefIdr 报文类型, document报文主体]
     **/
    public BizRuleSet ruleOption(String bizMsgDefIdr, Object document) {
        if (StringUtils.isEmpty(bizMsgDefIdr)) {
            return null;
        }
        Specification<BizRuleSet> bizRuleSetSpecification = new Specification<BizRuleSet>() {
            @Override
            public Predicate toPredicate(Root<BizRuleSet> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<Predicate>();//这里Predicate用来做运算类型或者是说查询条件
                predicates.add(criteriaBuilder.equal(root.get("status"), "0"));
                //criteriaBuilder.equal(Expression,字符串)，第一个参数是为哪个字段设置条件，第二个参数就是需要设置的值
                //解析一下这一句，就是where status(这个对应的是数据库里面字段)=0 查出来的数据放在这个链表里面
                //root 相当于 from   ，CriteriaBuilder相当于where后面的各种的条件，比如>,<,=等    criteriaQuery就相当于后面的条件比如orderby，或者where等等
                predicates.add(criteriaBuilder.equal(root.get("bizType"), bizMsgDefIdr));//添加数据库的规则
                Order prority = criteriaBuilder.asc(root.get("prority"));//根据prority进行正序排序
                if (predicates != null) {
                    criteriaQuery.where(predicates.toArray(new Predicate[0]));
                }
                return criteriaQuery.orderBy(prority).getRestriction();
            }
        };
        List<BizRuleSet> bizRuleSetList = bizRuleSetRepository.findAll(bizRuleSetSpecification);
        for (BizRuleSet bizRuleSet : bizRuleSetList) {
            Specification<BizRuleData> bizRuleDataSpecification = (Specification<BizRuleData>) (root, criteriaQuery, criteriaBuilder) -> {
                Predicate predicate = null;
                predicate = criteriaBuilder.equal(root.get("rid"), bizRuleSet.getId());
                if (predicate != null) {
                    criteriaQuery.where(predicate);
                }
                return criteriaQuery.getRestriction();
            };
            List<BizRuleData> bizRuleDataList = bizRuleDataRepository.findAll(bizRuleDataSpecification);
            boolean flag = bizRuleDataList!=null&&bizRuleDataList.size()!=0;
            for (BizRuleData bizRuleData : bizRuleDataList) {
                if (!ruleOption(document, bizRuleData)) {
                    flag = false;
                    break;
                }
            }
            if(flag) {
                return bizRuleSet;
            }
        }
       return null;
    }
}
