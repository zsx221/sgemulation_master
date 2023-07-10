package com.macrochina.net.dao;

import com.macrochina.net.entity.MessagePacs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MessagePacsRepository extends JpaRepository<MessagePacs, Integer>, JpaSpecificationExecutor {
    MessagePacs findByMsgDefAndBizMsgIdr(String msgDef, String bizMsgIdr);

    @Modifying
    @Query("delete from  message_pacs ")
    void deleteAll();
}