package com.nerogene.app.controller;

import com.nerogene.app.config.FlatFilePropertyConfig;
import com.nerogene.app.service.ReportingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "Reporting API", description = "API for generating reports based on flat file data")
@RestController
@RequestMapping("/api/reporting")
public class ReportingController {

    private final ReportingService reportService;
    private final FlatFilePropertyConfig propertyConfig;


    @Autowired
    public ReportingController(ReportingService reportService, FlatFilePropertyConfig config) {
        this.reportService = reportService;
        this.propertyConfig = config;
    }


    @ApiOperation(value = "Generate a PDF report from a flat file", notes = "Generates a PDF report based on the existing flat file")
    @PostMapping("")
    public ResponseEntity<byte[]> generateReportFromFlatFile() throws JRException {
        byte[] report = reportService.generateReportFromFlatFile(propertyConfig.getFullFilePath());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("filename", "flatfile-report.pdf");
        return new ResponseEntity<>(report, headers, HttpStatus.OK);
    }
}