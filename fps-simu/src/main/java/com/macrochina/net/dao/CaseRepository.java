package com.macrochina.net.dao;

import com.macrochina.net.entity.Case;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CaseRepository extends JpaRepository<Case,Integer>, JpaSpecificationExecutor {

    Page<Case> findAllByOrderByIdDesc(Specification<Case> cases, Pageable pageable);
}
