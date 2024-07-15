package com.alexisindustries.timetracker.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ExceptionHandlerControllerAdvice {
    @ExceptionHandler({UserAlreadyExistsException.class})
    public ResponseEntity<HttpStatus> handleUserAlreadyExistsException(UserAlreadyExistsException e) {
        log.error("Error occurred: {}", e.getMessage());
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
