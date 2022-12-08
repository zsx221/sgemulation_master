package com.macrochina.net.dto;

import lombok.Data;

@Data
public class TransactionLimits {

    private String customerId;
    private double amount;
    private String limitCategory;
    private String tracingId;

}
