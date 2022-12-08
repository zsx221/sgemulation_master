package com.macrochina.net.dao;

import com.macrochina.net.entity.API00204;
import com.macrochina.net.entity.SamplePacs008;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface API00204Repository extends JpaRepository<API00204,Integer>, JpaSpecificationExecutor {

    @Query(value="select * from api00204  " +
            "where id in " +
            " (select sample_id from case_list " +
            " where case_id = ?1" +
            " and sample_type = 'API00204')",nativeQuery = true)
    List<API00204> findAllCaseAPI00204(int caseId);
}
