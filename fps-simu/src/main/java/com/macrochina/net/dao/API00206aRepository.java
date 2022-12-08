package com.macrochina.net.dao;

import com.macrochina.net.entity.API00206a;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface API00206aRepository extends JpaRepository<API00206a,Integer>, JpaSpecificationExecutor<API00206a> {

//    @Query(value = "select api.*",countQuery = "select count(id) from api00206a",nativeQuery = true)
//    Page<API00206a> findAPI00206a(Pageable pageable);
}