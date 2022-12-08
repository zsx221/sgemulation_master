package com.macrochina.net.dto;

import lombok.Data;

@Data
public class InwardPaymentInstructions {
    private String endToEndId;
    private String opfTransactionId;
    private String externalSettlementReferenceId;
    private String getExternalSettlementReferenceType;
    private String rail;
    private String payeeAccountId;
    private String payeeAccountIdType;
    private String payerAccountId;
    private String payerAccountIdType;
    private String counterPartyBIC;
    private String amount;
    private String currency;
    private String movement;
    private String creditPayeeAccountId;
    private String debitPayeeAccountId;
    private String creditAmount;
    private String debitAmount;
    private String mandateId;

    private String paymentType;

    //713 新增
    private String bcsTransactionId;
    private String bcsInstructionId;
    private String paymentPurpose;

    public InwardPaymentInstructions(InwardPaymentInstructionsDto inwardPaymentInstructionsDto) {
        this.endToEndId = inwardPaymentInstructionsDto.getEndToEndId();
        this.opfTransactionId = inwardPaymentInstructionsDto.getOpfTransactionId();
        this.externalSettlementReferenceId = inwardPaymentInstructionsDto.getExternalSettlementReferenceId();
        this.getExternalSettlementReferenceType = inwardPaymentInstructionsDto.getExternalSettlementReferenceType();
        this.rail = inwardPaymentInstructionsDto.getRail();
        this.payeeAccountId = inwardPaymentInstructionsDto.getPayeeAccountId();

        this.paymentType = inwardPaymentInstructionsDto.getPaymentType();
        this.payeeAccountIdType = inwardPaymentInstructionsDto.getPayeeAccountIdType();
        this.payerAccountId = inwardPaymentInstructionsDto.getPayerAccountId();
        this.payerAccountIdType = inwardPaymentInstructionsDto.getPayerAccountIdType();
        this.counterPartyBIC = inwardPaymentInstructionsDto.getCounterPartyBIC();
        this.amount = inwardPaymentInstructionsDto.getAmount();
        this.currency = inwardPaymentInstructionsDto.getCurrency();
        this.movement = inwardPaymentInstructionsDto.getMovement();
        this.mandateId = inwardPaymentInstructionsDto.getMandateId();
        if ("CREDIT".equals(inwardPaymentInstructionsDto.getMovement())) {
            this.creditPayeeAccountId = inwardPaymentInstructionsDto.getPayeeAccountId();
            this.creditAmount = inwardPaymentInstructionsDto.getAmount();
        } else {
            this.debitPayeeAccountId = inwardPaymentInstructionsDto.getPayeeAccountId();
            this.debitAmount = inwardPaymentInstructionsDto.getAmount();
        }

        this.bcsTransactionId = inwardPaymentInstructionsDto.getBcsTransactionId();
        this.bcsInstructionId = inwardPaymentInstructionsDto.getBcsInstructionId();
        this.paymentPurpose = inwardPaymentInstructionsDto.getPaymentPurpose();
    }


}
