package com.alexisindustries.timetracker.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
@Slf4j
public class ExceptionHandlerControllerAdvice {
    @ExceptionHandler({UserAlreadyExistsException.class})
    public ResponseEntity<ExceptionDetailResponse> handleUserAlreadyExistsException(UserAlreadyExistsException e, WebRequest webRequest) {
        ExceptionDetailResponse errorDetails = ExceptionDetailResponse.builder()
                .errorTime(LocalDateTime.now())
                .message(e.getMessage())
                .path(webRequest.getDescription(false))
                .errorCode(ErrorCode.USER_ALREADY_EXISTS)
                .build();
        log.error("Error occurred: {}", e.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ExceptionDetailResponse> handleUsernameNotFoundException(UsernameNotFoundException e, WebRequest webRequest) {
        ExceptionDetailResponse errorDetails = ExceptionDetailResponse.builder()
                .errorTime(LocalDateTime.now())
                .message(e.getMessage())
                .path(webRequest.getDescription(false))
                .errorCode(ErrorCode.USERNAME_NOT_FOUND)
                .build();
        log.error("Error occurred: {}", e.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({RecordAlreadyExistsException.class})
    public ResponseEntity<ExceptionDetailResponse> handleRecordAlreadyExistsException(RecordAlreadyExistsException e, WebRequest webRequest) {
        ExceptionDetailResponse errorDetails = ExceptionDetailResponse.builder()
                .errorTime(LocalDateTime.now())
                .message(e.getMessage())
                .path(webRequest.getDescription(false))
                .errorCode(ErrorCode.RECORD_ALREADY_EXISTS)
                .build();
        log.error("Error occurred: {}", e.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ProjectAlreadyExistsException.class})
    public ResponseEntity<ExceptionDetailResponse> handleProjectAlreadyExistsException(ProjectAlreadyExistsException e, WebRequest webRequest) {
        ExceptionDetailResponse errorDetails = ExceptionDetailResponse.builder()
                .errorTime(LocalDateTime.now())
                .message(e.getMessage())
                .path(webRequest.getDescription(false))
                .errorCode(ErrorCode.PROJECT_ALREADY_EXISTS)
                .build();
        log.error("Error occurred: {}", e.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ObjectNotFoundInDatabaseException.class})
    public ResponseEntity<ExceptionDetailResponse> handleObjectNotFoundInDatabaseException(ObjectNotFoundInDatabaseException e, WebRequest webRequest) {
        ExceptionDetailResponse errorDetails = ExceptionDetailResponse.builder()
                .errorTime(LocalDateTime.now())
                .message(e.getMessage())
                .path(webRequest.getDescription(false))
                .errorCode(ErrorCode.OBJECT_NOT_FOUND)
                .build();
        log.error("Error occurred: {}", e.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({BadCredentialsException.class})
    public ResponseEntity<ExceptionDetailResponse> handleBadCredentialsException(BadCredentialsException e, WebRequest webRequest) {
        ExceptionDetailResponse errorDetails = ExceptionDetailResponse.builder()
                .errorTime(LocalDateTime.now())
                .message(e.getMessage())
                .path(webRequest.getDescription(false))
                .errorCode(ErrorCode.BAD_CREDENTIALS)
                .build();
        log.error("Error occurred: {}", e.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDetailResponse> handleGlobalException(Exception exception, WebRequest webRequest) {
        ExceptionDetailResponse errorDetails = ExceptionDetailResponse.builder()
                .errorTime(LocalDateTime.now())
                .message("Internal server error occurred")
                .path(webRequest.getDescription(false))
                .errorCode(ErrorCode.INTERNAL_SERVER_ERROR)
                .build();
        log.error("Error occurred: {}", exception.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
