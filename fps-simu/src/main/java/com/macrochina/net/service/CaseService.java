package com.macrochina.net.service;

import com.macrochina.net.dao.CaseListRepository;
import com.macrochina.net.dao.CaseRepository;
import com.macrochina.net.entity.Case;
import com.macrochina.net.dto.CaseDto;
import com.macrochina.net.entity.CaseList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import javax.persistence.criteria.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CaseService {
    Logger logger = LoggerFactory.getLogger(CaseService.class);
    @Autowired
    private CaseRepository caseRepository;

    @Autowired
    private CaseListRepository caseListRepository;

    public Case save(CaseDto caseDto) {
        Case res = new Case();
        BeanUtils.copyProperties(caseDto, res, "id");
        res.setState(0);
        res.setTimes(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        res = caseRepository.save(res);
        int id = res.getId();
        List<CaseList> caseLists = caseDto.getCaseLists();
        System.out.println(caseLists.toString());
        caseLists.forEach(x->x.setCaseId(id));
        caseLists.forEach(x->x.setState(0));
        caseListRepository.saveAll(caseLists);
        return res;
    }

    public Page<Case> findAllCaseByCdn(Integer page, Integer size, CaseDto molde) {
        Page<Case> rtn = null;
        Pageable pageable = PageRequest.of(page == 0 ? 0 : page - 1, size == 0 ? 10 : size,Sort.by("id").descending());
        Specification<Case> cases = new Specification<Case>() {

            @Override
            public Predicate toPredicate(Root<Case> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<Predicate>();
                if (!StringUtils.isEmpty(molde.getName())){
                    Path<String> name = root.get("name");
                    Predicate predicate = criteriaBuilder.like(name, "%" +molde.getName()+"%");
                    predicates.add(predicate);
                }
                if (!StringUtils.isEmpty(molde.getId())){
                    Path<String> id = root.get("id");
                    Predicate predicate  = criteriaBuilder.and(criteriaBuilder.like(criteriaBuilder.lower(id.as(String.class)), "%"+molde.getId()+"%"));
                    predicates.add(predicate);
                }
                if (predicates != null) {
                    criteriaQuery.where(predicates.toArray(new Predicate[0]));
                }
                return criteriaQuery.getRestriction();
            }
        };
        rtn = caseRepository.findAll(cases, pageable);

        return rtn;
    }

    public void deleteById(int id) {
       this.caseRepository.deleteById(id);

    }

    public Case findByid(int id) {
        return caseRepository.findById(id).get();
    }

    public Case update(Case res) {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        res.setTimes(sdf.format(new Date()));
        List<CaseList> caseLists = res.getCaseLists();
        int caseid = res.getId();
        caseListRepository.deleteByCaseId(caseid);
        caseLists.forEach(x->x.setCaseId(caseid));
        caseLists.forEach(x->x.setState(0));
        caseListRepository.saveAll(caseLists);
        return caseRepository.save(res);
    }
}
