package com.macrochina.net.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SamplePace008Dto {
    private String OriginatingBankBIC;
    private String CategoryPurpose;
    private String Currency;
    private String ChargeBearer;
    private String Creditor;
    private String Debtor;
    private String Txid;
    private List<Integer> ids;

}


