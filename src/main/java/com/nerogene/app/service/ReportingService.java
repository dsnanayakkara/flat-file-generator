package com.nerogene.app.service;

import com.nerogene.app.dto.FlatFileRecordDTO;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

@Service
public class ReportingService {

    @Value("${flatfile.generator.filepath}")
    private String outputFilePath;

    @Value("${flatfile.generator.jasper.report.name}")
    private String reportName;

    @Value("${flatfile.generator.jasper.report.format}")
    private String reportExtention;

    @Value("${flatfile.generator.jasper.report.template}")
    private String reportTemplate;

    private static final Logger LOG = LoggerFactory.getLogger(ReportingService.class);



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
}
