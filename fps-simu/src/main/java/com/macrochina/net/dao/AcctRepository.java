package com.macrochina.net.dao;

import com.macrochina.net.entity.Acct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface AcctRepository extends JpaRepository<Acct,Integer>, JpaSpecificationExecutor {

    public Acct findAcctByBankAcct(String bankAcct);
}