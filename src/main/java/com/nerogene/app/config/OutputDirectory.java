package com.nerogene.app.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.io.File;

@Component
public class OutputDirectory {

    private static final String OUTPUT_DIRECTORY_PATH = "./output";
    private static final Logger LOG = LoggerFactory.getLogger(OutputDirectory.class);

    @PostConstruct
    public void createOutputDirectory() {
        File outputDirectory = new File(OUTPUT_DIRECTORY_PATH);
        if (!outputDirectory.exists()) {
            boolean wasSuccessful = outputDirectory.mkdir();
            if (wasSuccessful) {
                LOG.info("Output directory created successfully.");
            } else {
                LOG.info("Output directory already exists.");
            }
        }
    }
}
