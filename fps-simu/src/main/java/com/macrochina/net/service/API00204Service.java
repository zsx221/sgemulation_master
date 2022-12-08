package com.macrochina.net.service;

import com.macrochina.net.dao.API00204Repository;
import com.macrochina.net.entity.API00204;
import com.macrochina.net.entity.BizRuleSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;
import java.util.List;

@Service
public class API00204Service {

    @Autowired
    private API00204Repository api00204Repository;

    @Autowired
    private FpsParamService fpsParamService;

    @Autowired
    private MessageService messageService;


    public API00204 save(API00204 api00204) {
        api00204Repository.save(api00204);
        return api00204;
    }
    public API00204 findAPI00204ById(Integer id){
        return api00204Repository.findById(id).get();
    }

    public void deleteById(int id) {
       this.api00204Repository.deleteById(id);

    }

    public API00204 update(API00204 res) {
        return api00204Repository.save(res);
    }

    public Page<API00204> findAll(Integer page, Integer limit, API00204 api00204){
        Page<API00204> rtn = null;
        Pageable pageable = PageRequest.of(page == 0 ? 0 : page - 1, limit == 0 ? 10 : limit, Sort.by("id").descending());
        Specification<BizRuleSet> bizRuleSetSpecification = new Specification<BizRuleSet>() {
            @Override
            public Predicate toPredicate(Root<BizRuleSet> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Predicate predicate = null;
            /*    if (!StringUtils.isEmpty(molde.getName())){
                    Path<String> name = root.get("name");
                    predicate = criteriaBuilder.like(name, "%" +molde.getName()+"%");
                }
                if (!StringUtils.isEmpty(molde.getResp_status())){
                    Path<String> resp_status = root.get("resp_status");
                    predicate = criteriaBuilder.like(resp_status, "%" +molde.getResp_status()+"%");
                }*/
                if (predicate!=null){

                    criteriaQuery.where(predicate);
                }
                return criteriaQuery.getRestriction();
            }
        };
        rtn = api00204Repository.findAll(bizRuleSetSpecification, pageable);
        return rtn;
    }

    public Page<API00204> queryAPI00204NotInIds(Integer page, Integer size, String ids) {
        Page<API00204> rtn = null;
        Pageable pageable = PageRequest.of(page == 0 ? 0 : page - 1, size == 0 ? 10 : size, Sort.by("id").descending());
        Specification<API00204> api00204Specification = new Specification<API00204>() {

            @Override
            public Predicate toPredicate(Root<API00204> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Predicate predicate = null;
                if(!StringUtils.isEmpty(ids)) {
                    String[] idStr = ids.split(",");
                    if (idStr != null && idStr.length > 0) {
                        Path<Integer> id = root.get("id");
                        CriteriaBuilder.In<Integer> in = criteriaBuilder.in(id);
                        for (String i : idStr) {
                            in.value(Integer.valueOf(i));//存入值
                        }
                        predicate = criteriaBuilder.and(criteriaBuilder.not(criteriaBuilder.and(in)));

                    }
                }

                if(predicate!=null){
                    criteriaQuery.where(predicate);
                }
                return criteriaQuery.getRestriction();
            }
        };
        rtn = api00204Repository.findAll(api00204Specification,pageable);
        return rtn;
    }

    public List<API00204> queryAPI00204( int caseId) {
        List<API00204> rtn = api00204Repository.findAllCaseAPI00204(caseId);
        return rtn;
    }

}
