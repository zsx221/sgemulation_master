package com.macrochina.net.dao;

import com.macrochina.net.dto.AddrsDto;
import com.macrochina.net.entity.Address;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address,Integer>, JpaSpecificationExecutor {

    @Query(value="select * from address " +
            "where id in (?1)",nativeQuery = true)
    List<Address> findAllAddressByIds(List<Integer> ids);

    @Query(value = "select count(a.id) from address a where a.regn_id = ?1 and a.regn_tp = 'NEWR' and a.status = 'ACTC' ",nativeQuery = true)
    int findCountByBicAndRegnId(String regnId);

    @Query(value="select * from address  " +
            "where id in " +
            " (select sample_id from case_list " +
            " where case_id = ?1" +
            " and sample_type = ?2)",nativeQuery = true)
    List<Address> findAllAddress(int caseId, String s);
    @Query(value = "select * from address a where a.val = ?1 and a.msg_def = ?2 and a.regn_tp = 'NEWR' and a.status = 'ACTC'",nativeQuery = true)
    List<Address> findByValAndMsgDef(String val,String msgDef);

    @Query(value = "select * from address a where a.agt = ?1 and a.msg_def = ?2 and a.regn_tp = 'NEWR' and a.status = 'ACTC'",nativeQuery = true)
    List<Address> findAllByBank(String bic,String msgDef);
//    @Query(value = "select * from address a where a.val = ?1 and a.regn_tp = 'NEWR' ",nativeQuery = true)
//    List<Address> findByValAndRegnTp(String val,String regnTp);
    @Query(value = "select * from address a where a.acct_id = ?1 and a.msg_def = ?2 and a.regn_tp = 'NEWR'  and a.status = 'ACTC'",nativeQuery = true)
    List<Address> findByAcctIdAndMsgDef(String acctId,String msgDef);
    @Query(value = "select * from address a where a.regn_id = ?1 and a.msg_def = ?2 and a.regn_tp = 'NEWR'  and a.status = 'ACTC'",nativeQuery = true)
    List<Address> findByRegnIdAndMsgDef(String regnId,String msgDef);
    @Query(value = "select * from address a where a.val = ?1 and a.acct_id = ?2  and a.msg_def = ?3 and a.regn_tp = 'NEWR'  and a.status = 'ACTC'",nativeQuery = true)
    List<Address> findAllByValAndAcctIdAndMsgDef(String val, String acctId, String msgDef);

    Address findOneByMsgDef(String msgDef);
    @Query(value = "select ms.msg_id msgId,ms.from_instn fromInstn,ms.to_instn toInstn,ms.msg_def msgDef,ad.regn_sts regnSts,ad.agt agt,ad.regn_id regnId,ad.regn_tp regnTp,ad.regn_sub_tp regnSubTp,ad.pre_authrsd preAuthrsd,ad.tp,ad.val,ad.acct_hldr_nm acctHldrNm,ad.cre_dt_tm creDtTm from address ad left join message ms on ms.org_msg_id = ad.msg_id where ms.msg_def in ?1"
            ,countQuery = "select count(*) from address ad left join message ms on ms.org_msg_id = ad.msg_id where ms.msg_def in ?1",nativeQuery = true)
    Page<AddrsDto> findAdrsAll(List<String> list,Pageable pageable);

    @Query(value = "select * from address a where  a.val = ?1 and a.regn_tp = 'NEWR' and a.status = 'ACTC' order by id desc limit 1",nativeQuery = true)
    Address findByPrxy(String val);

    @Query(value = "select * from address a where a.acct_id = ?1 and a.regn_tp = 'NEWR' and a.status = 'ACTC' limit 1",nativeQuery = true)
    Address findByAcctId(String acctId);

    @Query(value = "select * from address a where a.regn_id = ?1  and a.regn_tp = 'NEWR' and a.status = 'ACTC' limit 1",nativeQuery = true)
    Address findByRegnId(String regnId);

    @Query(value = "select * from address a where a.regn_id = ?1 and a.regn_tp = 'NEWR' and a.status = 'ACTC'",nativeQuery = true)
    List<Address> findListByRegnId(String regnId);

    @Query(value = "select * from address a where a.val = ?1 and a.acct_id = ?2 and a.regn_tp = 'NEWR' and a.status = 'ACTC'",nativeQuery = true)
    List<Address> findListByPrxyAndAcctId(String val,String acctId);

    @Query(value = "select * from address a where a.val = ?1 and a.regn_tp = 'NEWR' and a.status = 'ACTC'",nativeQuery = true)
    List<Address> findListByPrxy(String val);

    @Query(value = "select * from address a where a.acct_id = ?1 and a.regn_tp = 'NEWR' and a.status = 'ACTC'",nativeQuery = true)
    List<Address> findByListAcctId(String acctId);

    @Query(value = "select count(a.id) from address a where a.acct_id = ?1  and a.regn_tp = 'NEWR' and a.status = 'ACTC' ",nativeQuery = true)
    int findAddressByAcctId(String acctId);

    @Query(value = "select count(a.id) from address a where a.val = ?1  and a.regn_tp = 'NEWR' and a.status = 'ACTC' ",nativeQuery = true)
    int findAddressByPrxy(String val);

    @Query(value = "select count(a.id) from address a where a.regn_id = ?1  and a.regn_tp = 'NEWR' and a.status = 'ACTC' ",nativeQuery = true)
    int findAddressByRegnId(String regnId);
}