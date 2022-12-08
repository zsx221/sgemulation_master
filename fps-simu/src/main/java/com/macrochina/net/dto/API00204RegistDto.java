package com.macrochina.net.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.macrochina.net.entity.Address;
import lombok.Data;

import javax.persistence.*;

@Data
public class API00204RegistDto implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JsonProperty("ClientId")
    private String ClientId;

    @JsonProperty("CServiceType")
    private String CServiceType;

    @JsonProperty("CTxnId")
    private String CTxnId;

    @JsonProperty("RptIndc")
    private String RptIndc;

    @JsonProperty("CDateTime")
    private String CDateTime;

    private Address address;
}
