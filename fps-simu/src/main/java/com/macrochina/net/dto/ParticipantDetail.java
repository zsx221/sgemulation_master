package com.macrochina.net.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ParticipantDetail {

    @JsonProperty("PtcpDtlList")
    private PtcpDtlList ptcpDtlList;

}
@Data
class PtcpDtlList{
    @JsonProperty("PtcpDtl")
    List<PtcpDtl> PtcpDtl;
}
@Data
class PtcpDtl{
    @JsonProperty("PtcpCode")
    String PtcpCode;
    @JsonProperty("PtcpName")
    String PtcpName;
    @JsonProperty("Default")
    String Default;
    @JsonProperty("SignOn")
    String SignOn;
    @JsonProperty("Availability")
    String Availability;
}
