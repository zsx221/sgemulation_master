package com.macrochina.net.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.macrochina.net.entity.IndvPrsn;
import com.macrochina.net.entity.Org;
import lombok.Data;

@Data
public class API00206aDto  {

    @JSONField(name = "RegnTp")
    private String RegnTp;

    @JSONField(name = "RegnSubTp")
    private String RegnSubTp;

    @JSONField(name = "ProxyId")
    private String ProxyId;

    @JSONField(name = "ProxyType")
    private String ProxyType;

    @JSONField(name = "RegnId")
    private String RegnId;

    @JSONField(name = "DsplNm")
    private String DsplNm;

    @JSONField(name = "AcctID")
    private String AcctID;

    @JSONField(name = "AcctType")
    private String AcctType;

    @JSONField(name = "AcctName")
    private String AcctName;

    @JSONField(name = "AcctHldrType")
    private String AcctHldrType;

    @JSONField(name = "PreAuthrsd")
    private Boolean PreAuthrsd;

    @JSONField(name = "OrgName")
    private String OrgName;

    @JSONField(name = "RegnDt")
    private String RegnDt;

    @JSONField(name = "TpOfOrg")
    private String TpOfOrg;

    @JSONField(name = "Org")
    private Org Org;

    @JSONField(name = "IndvPrsn")
    private IndvPrsn IndvPrsn;

}
