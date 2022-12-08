package com.macrochina.net.dao;

import com.macrochina.net.entity.Message;
import org.hibernate.sql.Update;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message,Integer> , JpaSpecificationExecutor {

    @Modifying
    @Query("delete from  Message ")
    void deleteAll();
}