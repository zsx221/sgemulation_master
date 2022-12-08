package com.macrochina.net.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PayNowReportCallbacks {
    @JsonProperty("CTxnId")
    private String CTxnId;

    @JsonProperty("PrtryDataTp")
    private String PrtryDataTp;

    @JsonProperty("LookUpReqd")
    private String LookUpReqd;

    @JsonProperty("LookUpOnOwnCstmr")
    private String LookUpOnOwnCstmr;

    @JsonProperty("PrxyWthZeroLookUp")
    private String PrxyWthZeroLookUp;

    @JsonProperty("SucssfullyRegn")
    private String SucssfullyRegn;

    @JsonProperty("UsucssfullyRegn")
    private String UsucssfullyRegn;

    @JsonProperty("DeactvdRegn")
    private String DeactvdRegn;

    @JsonProperty("ArchvdRegn")
    private String ArchvdRegn;

    @JsonProperty("LiveRegn")
    private String LiveRegn;

    @JsonProperty("ReasonCode")
    private String ReasonCode;

    @JsonProperty("ReasonInfo")
    private String ReasonInfo;


}
