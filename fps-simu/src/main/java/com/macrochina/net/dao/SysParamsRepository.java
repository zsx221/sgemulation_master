package com.macrochina.net.dao;

import com.macrochina.net.entity.SysParams;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SysParamsRepository extends JpaRepository<SysParams,Long>, JpaSpecificationExecutor {

    Page<SysParams> findAll(Pageable pageable);

    SysParams findBySysCode(String sysCode);

}
