package com.macrochina.net.service;

import com.macrochina.net.dao.SysDictRepository;
import com.macrochina.net.entity.SysDict;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class SysDictService {

    @Autowired
    private SysDictRepository sysDictRepository;

    public List<SysDict> findAllByBizGroup(String bizGroup) {
        return sysDictRepository.findByBizGroup(bizGroup);
    }

    public void save(SysDict code) {
        sysDictRepository.save(code);
    }

    public SysDict findOneById(int id) {
        return sysDictRepository.findById(id).get();
    }

    public void deleteById(int id) {
        sysDictRepository.deleteById(id);
    }

    public Page<SysDict> findAll(Integer page, Integer size, SysDict code) {
        Pageable pageable = PageRequest.of(page == 0 ? 0 : page - 1, size == 0 ? 10 : size, Sort.by("id").descending());
        Specification<SysDict> specification = (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> list = new ArrayList<>();
            if (StringUtils.isNotBlank(code.getBizGroup())) {
                list.add(criteriaBuilder.like(root.get("bizGroup"), "%" + code.getBizGroup() + "%"));
            }
            if (StringUtils.isNotBlank(code.getBizCode())) {
                list.add(criteriaBuilder.like(root.get("bizCode"), "%" + code.getBizCode() + "%"));
            }
            if (StringUtils.isNotBlank(code.getBizValue())) {
                list.add(criteriaBuilder.like(root.get("bizValue"), "%" + code.getBizValue() + "%"));
            }
            return criteriaBuilder.and(list.toArray(new Predicate[0]));
        };
        return sysDictRepository.findAll(specification, pageable);
    }
}
