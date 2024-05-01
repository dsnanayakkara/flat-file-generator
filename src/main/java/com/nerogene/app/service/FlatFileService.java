package com.nerogene.app.service;

import com.nerogene.app.dto.FlatFileRecordDTO;
import com.nerogene.app.model.Body;
import com.nerogene.app.model.Header;
import com.nerogene.app.model.Trailer;
import com.nerogene.app.util.FilePathUtil;
import org.beanio.BeanIOException;
import org.beanio.BeanWriter;
import org.beanio.StreamFactory;
import org.beanio.builder.FixedLengthParserBuilder;
import org.beanio.builder.StreamBuilder;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class FlatFileService {

    private static final String INTERNAL_FILE_FORMAT = "fixedlength";


    public void generateFlatFile(List<FlatFileRecordDTO> records) throws BeanIOException, FileNotFoundException {

        StreamFactory factory = StreamFactory.newInstance();
        StreamBuilder builder = new StreamBuilder(FilePathUtil.getFileFormat())
                .format(INTERNAL_FILE_FORMAT)
                .parser(new FixedLengthParserBuilder())
                .addRecord(Header.class)
                .addRecord(Body.class)
                .addRecord(Trailer.class);
        factory.define(builder);

        final String fullPath = FilePathUtil.getFlatFilePath();

        Writer out = new OutputStreamWriter(new FileOutputStream(new File(fullPath)), StandardCharsets.UTF_8);
        BeanWriter writer = factory.createWriter(FilePathUtil.getFileFormat(), out);
        Header header = new Header();
        writer.write(header);
        for (FlatFileRecordDTO body : records) {
            Body beanIoBody = new Body(body.getReferenceNo(), body.getAmount().toString());
            writer.write(beanIoBody);
        }
        Trailer trailer = new Trailer();
        writer.write(trailer);
        writer.flush();
        writer.close();

    }

}
