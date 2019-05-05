package com.travix.medusa.busyflights.exception;

import com.travix.medusa.busyflights.service.impl.SupplierDataProcessingServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.logging.Logger;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOG = Logger.getLogger(GlobalExceptionHandler.class.getName());


    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> globalException(Exception ex){
        LOG.info("Exception occured in server "+ex.getMessage());
       return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);

    }
}
