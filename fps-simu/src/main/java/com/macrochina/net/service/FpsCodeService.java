package com.macrochina.net.service;

import com.macrochina.net.dao.FpsCodeRepository;
import com.macrochina.net.entity.FpsCode;
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
public class FpsCodeService {

    @Autowired
    private FpsCodeRepository fpsCodeRepository;

    public void save(FpsCode code) {
        fpsCodeRepository.save(code);
    }

    public FpsCode findOneById(int id) {
        return fpsCodeRepository.findById(id).get();
    }

    public void deleteById(int id) {
        fpsCodeRepository.deleteById(id);
    }

    public Page<FpsCode> findAll(Integer page, Integer size, FpsCode code) {
        Pageable pageable = PageRequest.of(page == 0 ? 0 : page - 1, size == 0 ? 10 : size);
        Specification<FpsCode> FpsCodeSpecification = (root, criteriaQuery, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<Predicate>();
            if (StringUtils.isNotBlank(code.getRsnCode())) {
                Path<String> rsnCode = root.get("rsnCode");
                Predicate predicate  = criteriaBuilder.like(rsnCode, "%" + code.getRsnCode() + "%");
                predicates.add(predicate);
            }
            if(StringUtils.isNotBlank(code.getCode())){
                Path<String> bizCode = root.get("code");
                Predicate predicate  = criteriaBuilder.equal(bizCode, code.getCode());
                predicates.add(predicate);
            }
            if (predicates != null) {
                criteriaQuery.where(predicates.toArray(new Predicate[0]));
            }
            return criteriaQuery.getRestriction();
        };
        return fpsCodeRepository.findAll(FpsCodeSpecification, pageable);
    }
}
