package com.nerogene.app.controller;

import com.nerogene.app.dto.FlatFileRecordDTO;
import com.nerogene.app.service.FlatFileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.util.List;

@Api(value = "Flat File API", description = "API for generating flat file based on request data")
@RestController
@RequestMapping("/api/flatfile")
public class FlatFileServiceController {

    private final FlatFileService flatFileService;


    @Autowired
    public FlatFileServiceController(FlatFileService flatFileService) {
        this.flatFileService = flatFileService;
    }

    @ApiOperation(value = "Generate a flat file", notes = "Generates a flat file based on the provided request data")
    @PostMapping("")
    public ResponseEntity<String> generateFlatFile(@ApiParam(value = "List of record DTOs to be included in created flat file", required = true)
                                                   @RequestBody List<FlatFileRecordDTO> records) throws FileNotFoundException {
        flatFileService.generateFlatFile(records);
        return ResponseEntity.ok("Flat file generated successfully.");
    }

}
