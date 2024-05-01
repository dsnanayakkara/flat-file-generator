package com.nerogene.app.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.io.File;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "flatfile.generator")
public class FlatFilePropertyConfig {

    private String filepath;
    private String filename;
    private String fileformat;

    public String getFullFilePath() {
        return filepath + File.separator + filename + fileformat;
    }


}
