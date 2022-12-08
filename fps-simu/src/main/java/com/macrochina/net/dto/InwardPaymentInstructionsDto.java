package com.macrochina.net.dto;

import lombok.Data;

@Data
public class InwardPaymentInstructionsDto {
    private String endToEndId;
    private String opfTransactionId;
    private String externalSettlementReferenceId;
    private String externalSettlementReferenceType;
    private String rail;
    private String paymentType;
    private String payeeAccountId;
    private String payeeAccountIdType;
    private String payerAccountId;
    private String payerAccountIdType;
    private String counterPartyBIC;
    private String amount;
    private String currency;
    private String movement;
    private String mandateId;

    private String payeeName;

    private String payerName;

    //713 新增
    private String bcsTransactionId;
    private String bcsInstructionId;
    private String paymentPurpose;
}
