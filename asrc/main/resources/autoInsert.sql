INSERT INTO acct (accountid_type,acct_nm,bank_acct,bic,crop_proxy,dspl_nm,gvn_nm,issr,lt_nm,md_nm,msisdn,nrig,org,org_nm,org_tp,phx_card,pty,remark,state,uen,vpa) VALUES
    ('AIIN','Mr. W S Dunleavy','8BF39A21244291EA6D15C3B5A60756204DE622ECF5BDDE41200674C0F0EC155F','BANKSGSG707','COPY8080205050','Wendy·Samuel·Dunleavy','Wendy','','Dunleavy','Samuel','+6580805050','W8080555N','Individual Person','','','dc1c2683-01a1-4041-bfa9-0728d05dcd6e','','','2','COMPANY005','+6580806060#QX50');





select setval(pg_get_serial_sequence('biz_rule_set', 'id'),100027,false);