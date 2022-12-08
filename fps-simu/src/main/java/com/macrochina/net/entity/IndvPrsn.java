package com.macrochina.net.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

@Data
public class IndvPrsn {

    @JSONField(name = "GvnNm")
    private String GvnNm;

    @JSONField(name = "MddlNm")
    private String MddlNm;

    @JSONField(name = "LastNm")
    private String LastNm;

}
