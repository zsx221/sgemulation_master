package com.macrochina.net.dto;

import lombok.Data;

import java.util.List;

@Data
public class PostingRequests{
    private String paymentInstructionId;
    private String postingRequestId;
    private String opfTransactionId;
    private String senderReference;
    private String valueDate;
    private String transactionType;
    private String description;
    private String rail;
    private String reverseTransaction;
    private String identifier;
    private String amount;

    //713 新增
    private String bcsTransactionId;
    private String bcsInstructionId;

    public PostingRequests(PostingRequestsDto dto){
        this.paymentInstructionId= dto.getPaymentInstructionId();
        this.postingRequestId= dto.getPostingRequestId();
        this.senderReference= dto.getSenderReference();
        this.valueDate= dto.getValueDate();
        this.opfTransactionId= dto.getOpfTransactionId();
        this.transactionType= dto.getTransactionType();
        this.description= dto.getDescription();
        this.rail= dto.getRail();
        this.reverseTransaction= dto.getReverseTransaction();
        List<Posting> postings = dto.getPostings();
        boolean isACID = true;
        for(Posting posting : postings){
            if("BBAN".equals(posting.getIdentifierType())||"AIIN".equals(posting.getIdentifierType())){
                this.identifier= posting.getIdentifier();
                this.amount= posting.getAmount();
                isACID = false;
                break;
            }
        }
        if(isACID){
            this.identifier= postings.get(0).getIdentifier();
            this.amount= postings.get(0).getAmount();
        }

        this.bcsTransactionId = dto.getBcsTransactionId();
        this.bcsInstructionId = dto.getBcsInstructionId();
    }
}