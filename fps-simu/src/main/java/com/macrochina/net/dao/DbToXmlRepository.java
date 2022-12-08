package com.macrochina.net.dao;

import com.macrochina.net.entity.DbToXml;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface DbToXmlRepository extends JpaRepository<DbToXml,Long>, JpaSpecificationExecutor {
    //Page<XmlToDb> findAll(Pageable pageable);

   /* @Query(value="select  sys_Code ,sys_Value from sysParams",nativeQuery = true)
    Map<String,String> loadAll();*/

    List<DbToXml> findByTargetClassName(String targetClassName);

}
