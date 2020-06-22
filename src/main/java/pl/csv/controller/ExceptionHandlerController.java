package pl.csv.controller;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.IOException;

@ControllerAdvice
@Slf4j
public class ExceptionHandlerController  {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void handleException(Exception e) {
        log.error("Exception occurred", e);
    }


    @ExceptionHandler(IOException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void handleIOException(IOException e) {
        log.error("IOException occurred", e);
    }

    @ExceptionHandler(CsvDataTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void handleCsvDataTypeMismatchException(CsvDataTypeMismatchException e) {
        log.error("Error occurred", e);
    }

}
