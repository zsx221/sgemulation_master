package com.macrochina.net.dao;

import com.macrochina.net.entity.FpsParam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FpsParamRepository extends JpaRepository<FpsParam,Integer> {

//    FpsParam findOneByBizType(String bizType);

}