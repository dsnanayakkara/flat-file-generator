package com.nerogene.app.controller;

import com.nerogene.app.dto.FlatFileRecordDTO;
import com.nerogene.app.service.ReportingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value = "ReportingController", description = "API for generating reports based on request data")
@RestController
@RequestMapping("/api/reporting")
public class ReportingController {

    private final ReportingService reportService;

    @Autowired
    public ReportingController(ReportingService reportService) {
        this.reportService = reportService;
    }

    @ApiOperation(value = "Generate a PDF report", notes = "Generates a PDF report based on the provided request data")
    @PostMapping("")
    public ResponseEntity<byte[]> generateReport(@ApiParam(value = "List of flat file record DTOs to be included in the report", required = true)
                                                 @RequestBody List<FlatFileRecordDTO> records) throws JRException {
        byte[] report = reportService.generateReport(records);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("filename", "flatfile-report.pdf");
        return new ResponseEntity<>(report, headers, HttpStatus.OK);
    }
}