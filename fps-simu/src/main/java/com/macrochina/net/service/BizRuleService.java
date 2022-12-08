package com.macrochina.net.service;

import com.macrochina.net.dao.BizRuleDataRepository;
import com.macrochina.net.dao.BizRuleSetRepository;
import com.macrochina.net.dto.BizRuleSetDto;
import com.macrochina.net.entity.BizRuleData;
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
import java.util.ArrayList;
import java.util.List;

@Service
public class BizRuleService {
    @Autowired
    private BizRuleDataRepository bizRuleDataRepository;
    @Autowired
    private BizRuleSetRepository bizRuleSetRepository;
    public BizRuleSet saveSet(BizRuleSet bizRuleSet) {
        return bizRuleSetRepository.save(bizRuleSet);
    }



    public BizRuleSet findOneSetById(int id) {
        return bizRuleSetRepository.findOneById(id);
    }

    public void deleteSetById(int id) {
        bizRuleSetRepository.deleteById(id);
    }

    public void deleteDataByRid(int rid) {
        bizRuleDataRepository.deleteAllByRid(rid);
    }

    public Page<BizRuleSet> findAllRuleSetByCdn(Integer page, Integer size, BizRuleSetDto molde) {

        Page<BizRuleSet> rtn = null;
        Pageable pageable = PageRequest.of(page == 0 ? 0 : page - 1, size == 0 ? 10 : size, Sort.by("id").descending());
        Specification<BizRuleSet> bizRuleSetSpecification = new Specification<BizRuleSet>() {
            @Override
            public Predicate toPredicate(Root<BizRuleSet> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<Predicate>();
                if (!StringUtils.isEmpty(molde.getName())){
                    Path<String> name = root.get("name");
                    Predicate predicate = criteriaBuilder.like(name, "%" +molde.getName()+"%");
                    predicates.add(predicate);
                }
                if (!StringUtils.isEmpty(molde.getResp_status())){
                    Path<String> resp_status = root.get("resp_status");
                    Predicate predicate = criteriaBuilder.and(criteriaBuilder.like(resp_status, "%" +molde.getResp_status()+"%"));
                    predicates.add(predicate);
                }
                if (predicates != null) {
                    criteriaQuery.where(predicates.toArray(new Predicate[0]));
                }
                return criteriaQuery.getRestriction();
            }
        };
        rtn = bizRuleSetRepository.findAll(bizRuleSetSpecification, pageable);
        return rtn;
    }

    public List<BizRuleData> findAllRuleDataByRid(int id) {
        return bizRuleDataRepository.findAllByRid(id);
    }

    public void saveAllDatas(List<BizRuleData> bizRuleDatas) {
        bizRuleDataRepository.saveAll(bizRuleDatas);
    }
}
