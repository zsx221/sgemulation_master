package com.macrochina.net.dao;

import com.macrochina.net.entity.SamplePacs008;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SamplePacs008Repository extends JpaRepository<SamplePacs008,Integer> , JpaSpecificationExecutor {

//    @Query(value="SELECT *" +
//            " FROM sample_pacs008 as sa" +
//            " WHERE sa.txid = ?1",nativeQuery = true)
//    Page<SamplePacs008> findAllByTxid(String txid, Pageable pageable);
    @Query(value="select sp.* from sample_pacs008 sp " +
            "left join case_list  cl " +
            "on sp.id = cl.sample_id " +
            "where cl.case_id= ?1",nativeQuery = true)
    List<SamplePacs008> findAllSamplePacs008ByCastId(int id);

    @Query(value="select * from sample_pacs008  " +
            "where id in " +
            " (select sample_id from case_list " +
            " where case_id = ?1" +
            " and sample_type = ?2)",nativeQuery = true)
    List<SamplePacs008> findAllSamplePacs008(int caseId, String s);
}
