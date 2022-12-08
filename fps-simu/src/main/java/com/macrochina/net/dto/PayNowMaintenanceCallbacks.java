package com.macrochina.net.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class PayNowMaintenanceCallbacks {
    @JsonProperty("CTxnId")
    private String CTxnId;

    @JsonProperty("Status")
    private String Status;

    @JsonProperty("ReasonCode")
    private String ReasonCode;

    @JsonProperty("ReasonInfo")
    private String ReasonInfo;

    @JsonProperty("RegnIds")
    private List<RegnId> RegnIds;

    @JsonProperty("RegistedAgentBIC")
    private String RegistedAgentBIC;

}
@Data
class RegnId{
    @JsonProperty("RegnId")
    private String RegnId;
}
