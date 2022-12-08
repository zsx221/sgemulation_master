package com.macrochina.net.dao;

import com.macrochina.net.entity.FpsCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface FpsCodeRepository extends JpaRepository<FpsCode,Integer>, JpaSpecificationExecutor {

}