package com.macrochina.net.dto;

import com.macrochina.net.entity.CaseList;
import lombok.Data;

import java.util.List;

@Data
public class CaseDto {

    private Integer id;
    //private String prtpntCd;
    private String name;
    private int count;
    private String remark;
    private List<CaseList> caseLists;
}
