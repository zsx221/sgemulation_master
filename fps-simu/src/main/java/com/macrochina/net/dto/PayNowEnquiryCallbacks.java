package com.macrochina.net.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class PayNowEnquiryCallbacks {
    @JsonProperty("CTxnId")
    private String CTxnId;

    @JsonProperty("Status")
    private String Status;

    @JsonProperty("ReasonCode")
    private String ReasonCode;

    @JsonProperty("ReasonInfo")
    private String ReasonInfo;

    @JsonProperty("Rspn")
    private List<Rspn> Rspn;
}
@Data
class Rspn{
    @JsonProperty("RegnId")
    private String RegnId;
    @JsonProperty("DsplNm")
    private String DsplNm;
    @JsonProperty("ParticipantBIC")
    private String ParticipantBIC;
    @JsonProperty("ProxyId")
    private String ProxyId;
    @JsonProperty("ProxyType")
    private String ProxyType;
    @JsonProperty("ProxyStatus")
    private String ProxyStatus;
    @JsonProperty("AgentBIC")
    private String AgentBIC;
    @JsonProperty("AcctID")
    private String AcctID;
    @JsonProperty("AcctType")
    private String AcctType;
    @JsonProperty("AcctName")
    private String AcctName;
    @JsonProperty("AcctStatus")
    private String AcctStatus;

    @JsonProperty("RegnSts")
    private String RegnSts;

    @JsonProperty("RegnDtTm")
    private String RegnDtTm;

    @JsonProperty("PreAuthrsd")
    private String PreAuthrsd;

    @JsonProperty("AcctHldr")
    private List<AcctHldr> AcctHldr;

}
@Data
class AcctHldr{
    @JsonProperty("AcctHldrType")
    private String AcctHldrType;

    @JsonProperty("OrgName")
    private String OrgName;

    @JsonProperty("RegnDt")
    private String RegnDt;

    @JsonProperty("TpOfOrg")
    private String TpOfOrg;

    @JsonProperty("OrgIdIssr")
    private String OrgIdIssr;

    @JsonProperty("GvnNm")
    private String GvnNm;

    @JsonProperty("MddlNm")
    private String MddlNm;

    @JsonProperty("LastNm")
    private String LastNm;


}