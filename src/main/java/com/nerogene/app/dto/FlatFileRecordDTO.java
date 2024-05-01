package com.nerogene.app.dto;

import lombok.Data;

import java.math.BigDecimal;


@Data
public class FlatFileRecordDTO {
    private String referenceNo;
    private BigDecimal amount;
    private String date;
    private String status;
    private String remark;

}
