package com.macrochina.net.dao;

import com.macrochina.net.entity.Case;
import com.macrochina.net.entity.CaseList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface CaseListRepository extends JpaRepository<CaseList,Integer>, JpaSpecificationExecutor {
    @Modifying
    @Transactional
    void deleteByCaseId(int id);

    List<CaseList> findAllByCaseId(int id);

    @Query(value="select sample_id from case_list " +
            "where case_id = ?1" +
            " and sample_type = ?2",nativeQuery = true)
    List<Integer> findSampleIdBySampleType(int Caseid,String s);
}
