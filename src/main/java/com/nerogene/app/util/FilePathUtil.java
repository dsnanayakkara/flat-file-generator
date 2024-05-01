package com.nerogene.app.util;

import org.springframework.beans.factory.annotation.Value;

import java.io.File;

public final class FilePathUtil {
    @Value("${flatfile.generator.filepath}")
    private static String filePath;
    @Value("${flatfile.generator.filename}")
    private static String fileName;
    @Value("${flatfile.generator.fileformat}")
    private static String fileFormat;

    public static String getFlatFilePath() {
        return filePath + File.separator + fileName + fileFormat;
    }

    public static String getFileName() {
        return fileName;
    }

    public static String getFileFormat() {
        return fileFormat;
    }

}
