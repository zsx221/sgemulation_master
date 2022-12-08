package com.macrochina.net.dao;

import com.macrochina.net.entity.SysDict;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface SysDictRepository extends JpaRepository<SysDict,Integer>, JpaSpecificationExecutor {
    List<SysDict> findByBizGroup(String bizGroup);
}
