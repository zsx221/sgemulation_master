package com.macrochina.net.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

@Data
public class Org  {

    @JSONField(name = "OrgName")
    private String OrgName;

    @JSONField(name = "RegnDt")
    private String RegnDt;

    @JSONField(name = "TpOfOrg")
    private String TpOfOrg;

}
