package com.macrochina.net.service;

import com.macrochina.net.dao.AcctRepository;
import com.macrochina.net.entity.Acct;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Service
public class AcctService {

    @Autowired
    private AcctRepository acctRepository;

    public void save(Acct acct) {
        acctRepository.save(acct);
    }

    public Acct findOneById(int id) {
        return acctRepository.findById(id).get();
    }

    public void deleteById(int id) {
        acctRepository.deleteById(id);
    }



    public Page<Acct> findAll(Integer page, Integer size, Acct acct) {
        Pageable pageable = PageRequest.of(page == 0 ? 0 : page - 1, size == 0 ? 10 : size);
        Specification<Acct> acctSpecification = (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> list = new ArrayList<>();
            if (StringUtils.isNotBlank(acct.getOrg())) { //Individual Person
                Path<String> org = root.get("org");
                list.add(criteriaBuilder.like(org, "%" + acct.getOrg() + "%"));
            }else {
                list.add(criteriaBuilder.not(criteriaBuilder.in(root.get("org")).value("Individual Person")));
            }
            if (StringUtils.isNotBlank(acct.getMsisdn())) {
                Path<String> msisdn = root.get("msisdn");
                list.add(criteriaBuilder.like(msisdn, "%" + acct.getMsisdn() + "%"));
            }
            if (StringUtils.isNotBlank(acct.getDsplNm())) {
                Path<String> dsplNm = root.get("dsplNm");
                list.add(criteriaBuilder.like(dsplNm, "%" + acct.getDsplNm() + "%"));
            }
            return criteriaBuilder.and(list.toArray(new Predicate[0]));
        };
        return acctRepository.findAll(acctSpecification, pageable);
    }
    public Acct findAcctByBankAcct(String bankAcct) {
        return acctRepository.findAcctByBankAcct(bankAcct);
    }
}
