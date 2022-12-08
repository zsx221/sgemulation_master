package com.macrochina.net.dao;

import com.macrochina.net.entity.BizRuleData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BizRuleDataRepository extends JpaRepository<BizRuleData,Integer>, JpaSpecificationExecutor {
    @Transactional
    void deleteAllByRid(int rid);

    List<BizRuleData> findAllByRid(int id);
}
