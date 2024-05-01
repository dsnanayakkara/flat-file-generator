package com.nerogene.app.service;

import com.nerogene.app.dto.FlatFileRecordDTO;
import com.nerogene.app.util.DateUtil;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class ReportingService {

    private static final Logger LOG = LoggerFactory.getLogger(ReportingService.class);
    @Value("${flatfile.generator.filepath}")
    private String outputFilePath;
    @Value("${flatfile.generator.jasper.report.name}")
    private String reportName;
    @Value("${flatfile.generator.jasper.report.format}")
    private String reportExtention;
    @Value("${flatfile.generator.jasper.report.template}")
    private String reportTemplate;

    private static FlatFileRecordDTO getFlatFileRecordDTO(String line) {
        LOG.info("Parsing line: {}", line);
        LOG.debug("Line length is : {}", line.length());
        String referenceNo = line.substring(19, 34).trim();
        String amount = line.substring(39).trim();
        FlatFileRecordDTO dto = new FlatFileRecordDTO();
        dto.setReferenceNo(referenceNo);
        dto.setAmount(new BigDecimal(amount));
        dto.setDate(DateUtil.getFormattedDateString());
        //adding rest of the records manually as they are not present in the flat file
        dto.setStatus("New");
        dto.setRemark("Awaiting confirmation");
        return dto;
    }

    private static List<FlatFileRecordDTO> readFlatFile(String flatFilePath) {
        List<FlatFileRecordDTO> flatFileBodyRecords = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(flatFilePath))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                // skipping header and trailer records
                if (line.startsWith("H") || line.startsWith("T")) {
                    continue;
                }
                // parsing body records based on fixed lengths
                FlatFileRecordDTO dto = getFlatFileRecordDTO(line);
                flatFileBodyRecords.add(dto);
            }
        } catch (IOException e) {
            LOG.info("Error reading flat file: {}", e.getMessage());
        }

        return flatFileBodyRecords;
    }

    public byte[] generateReport(List<FlatFileRecordDTO> records) throws JRException {
        LOG.info("Reading report template from : {}", reportTemplate);
        InputStream reportStream = getClass().getResourceAsStream("/" + reportTemplate);
        JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(records);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap<>(), dataSource);
        String exportPath = outputFilePath + File.separator + reportName + reportExtention;
        LOG.info("Exporting report to: {}", exportPath);
        JasperExportManager.exportReportToPdfFile(jasperPrint, exportPath);
        LOG.info("Report exported successfully.");
        LOG.info("Sending generated report to client.");
        return JasperExportManager.exportReportToPdf(jasperPrint);
    }

    public byte[] generateReportFromFlatFile(String flatFilePath) throws JRException {
        LOG.info("Generating report from flat file: {}", flatFilePath);
        List<FlatFileRecordDTO> recordDTOS = readFlatFile(flatFilePath);
        LOG.debug("Read {} records from flat file.", recordDTOS.size());
        return generateReport(recordDTOS);
    }
}




