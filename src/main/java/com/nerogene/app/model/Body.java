package com.nerogene.app.model;
import lombok.Getter;
import lombok.Setter;
import org.beanio.annotation.Field;
import org.beanio.annotation.Record;

@Setter
@Getter
@Record
public class Body {
    //converted to zero based indexes (for beanIO)  from 1 based indexes in specification.
    //assumption : end = start + length - 1 (inclusive)
    @Field(at = 19, length = 14, padding = ' ')
    private String referenceNo;

    @Field(at = 39, length = 16, padding = ' ')
    private String amount;

    public Body() {
    }

    public Body(String referenceNo, String amount) {
        this.referenceNo = referenceNo;
        this.amount = amount;
    }
}
