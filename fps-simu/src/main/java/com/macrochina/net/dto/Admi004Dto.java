package com.macrochina.net.dto;

import lombok.Data;

import javax.xml.datatype.XMLGregorianCalendar;
import java.util.List;
@Data
public class Admi004Dto {
    private String evtCd;

    private List<String> evtParam;

    private String evtDesc;

    private XMLGregorianCalendar evtTm;
}
