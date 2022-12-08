package com.macrochina.net.dao;

import com.macrochina.net.entity.XmlSign;
import com.macrochina.net.entity.XmlToDb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface XmlSignRepository  extends JpaRepository<XmlSign,Long>, JpaSpecificationExecutor {
    List<XmlSign> findByClassName(String className);
}
