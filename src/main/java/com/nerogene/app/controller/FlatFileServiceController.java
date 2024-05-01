package com.nerogene.app.controller;

import com.nerogene.app.dto.FlatFileRecordDTO;
import com.nerogene.app.service.FlatFileService;
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

import java.io.FileNotFoundException;
import java.util.List;

@Api(value = "ReportingController", description = "API for generating flat file based on request data")
@RestController
@RequestMapping("/api/flatfile")
public class FlatFileServiceController {

    private final FlatFileService flatFileService;
    private final ReportingService reportingService;


    @Autowired
    public FlatFileServiceController(FlatFileService flatFileService, ReportingService reportingService) {
        this.flatFileService = flatFileService;
        this.reportingService = reportingService;
    }

    @ApiOperation(value = "Generate a flat file", notes = "Generates a flat file based on the provided request data")
    @PostMapping("")
    public ResponseEntity<String> generateFlatFile(@ApiParam(value = "List of record DTOs to be included in created flat file", required = true)
                                                   @RequestBody List<FlatFileRecordDTO> records) throws FileNotFoundException {
        flatFileService.generateFlatFile(records);
        return ResponseEntity.ok("Flat file generated successfully.");
    }

    @ApiOperation(value = "Generate a flat file and create a report", notes = "Generates a flat file and a report based on the provided request data")
    @PostMapping("/report")
    public ResponseEntity<byte[]> generateFlatFileWithReport(@ApiParam(value = "List of record DTOs to be included in created flat file and the report", required = true)
                                                             @RequestBody List<FlatFileRecordDTO> records) throws FileNotFoundException, JRException {
        String filePath =  flatFileService.generateFlatFile(records);
        byte[] report = reportingService.generateReportFromFlatFile(filePath);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("filename", "flatfile-report.pdf");
        return new ResponseEntity<>(report, headers, HttpStatus.OK);
    }
}
