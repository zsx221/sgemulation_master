package com.macrochina.net.dao;

import com.macrochina.net.entity.BizRuleSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BizRuleSetRepository extends JpaRepository<BizRuleSet,Integer>,JpaSpecificationExecutor {

    BizRuleSet findOneByName(String name);

    BizRuleSet findOneById(int id);
}
