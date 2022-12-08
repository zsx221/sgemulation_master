package com.macrochina.net.dto;

import lombok.Data;

import java.util.List;
@Data
public class PostingRequestsDto {
    private String paymentInstructionId;
    private String postingRequestId;
    private String opfTransactionId;
    private String senderReference;
    private String valueDate;
    private String transactionType;
    private String description;
    private String rail;
    private String reverseTransaction;
    private List<Posting> postings;

    //713 新增
    private String bcsTransactionId;
    private String bcsInstructionId;

}
@Data
class Posting {
    private String identifier;
    private String identifierType;
    private String movement;
    private String currency;
    private String amount;
}