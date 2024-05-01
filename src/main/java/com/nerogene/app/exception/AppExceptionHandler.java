package com.nerogene.app.exception;

import net.sf.jasperreports.engine.JRException;
import org.beanio.BeanIOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.FileNotFoundException;

@ControllerAdvice
public class AppExceptionHandler {
    private static final Logger LOG = LoggerFactory.getLogger(AppExceptionHandler.class);
    @ExceptionHandler(BeanIOException.class)
    public ResponseEntity<String> handleBeanIOException(BeanIOException ex) {
        LOG.error("Error generating flat file: {}", ex.getMessage(), ex);
        return new ResponseEntity<>("Error generating flat file: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(FileNotFoundException.class)
    public ResponseEntity<String> handleFileNotFoundException(FileNotFoundException ex) {
        LOG.error("File not found: {}", ex.getMessage(), ex);
        return new ResponseEntity<>("File not found: " + ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(JRException.class)
    public ResponseEntity<String> handleJRException(JRException ex) {
        LOG.error("Error generating report: {}", ex.getMessage(), ex);
        return new ResponseEntity<>("Error generating report: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
