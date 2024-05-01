package com.nerogene.app.model;

import lombok.Getter;
import lombok.Setter;
import org.beanio.annotation.Field;
import org.beanio.annotation.Record;


@Setter
@Getter
@Record
public class Header {
    //converted to zero based indexes (for beanIO) from 1 based indexes in specification.
    //assumption : end = start + length - 1 (inclusive)
    @Field(at = 0, length = 1, padding = ' ')
    private String recordType = "H";

    @Field(at = 13, length = 15, padding = ' ')
    private String fileType = "FLAT_FILE";

    public Header() {

    }

}
