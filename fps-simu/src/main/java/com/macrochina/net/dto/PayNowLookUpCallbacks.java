package com.macrochina.net.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PayNowLookUpCallbacks {
    @JsonProperty("CTxnId")
    private String CTxnId;

    @JsonProperty("Status")
    private String Status;

    @JsonProperty("ReasonCode")
    private String ReasonCode;

    @JsonProperty("ReasonInfo")
    private String ReasonInfo;

    @JsonProperty("ProxyId")
    private String ProxyId;

    @JsonProperty("ProxyType")
    private String ProxyType;

    @JsonProperty("LkUpRef")
    private String LkUpRef;

    @JsonProperty("Regn")
    private Regn Regn;
}
@Data
class Regn{

    @JsonProperty("RegnId")
    private String RegnId;

    @JsonProperty("DsplNm")
    private String DsplNm;

    @JsonProperty("AgentBIC")
    private String AgentBIC;

    @JsonProperty("AcctID")
    private String AcctID;

    @JsonProperty("AcctType")
    private String AcctType;

    @JsonProperty("AcctName")
    private String AcctName;

    @JsonProperty("PreAuthrsd")
    private boolean PreAuthrsd;
}