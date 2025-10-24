package com.tekpyramid.movietek.Exception;

import com.tekpyramid.movietek.Response.FailureResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(DuplicateRecord.class)
    public ResponseEntity<FailureResponse> duplicateRecordHandler(DuplicateRecord exception){

        FailureResponse failureResponse = new FailureResponse();

        failureResponse.setData(null);
        failureResponse.setError(true);
        failureResponse.setHttpStatus(HttpStatus.CONFLICT);
        failureResponse.setMessage(exception.getMessage());

        return ResponseEntity.status(failureResponse.getHttpStatus()).body(failureResponse);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> validationHandler(MethodArgumentNotValidException exception){
        Map<String, String> errors = new HashMap<>();

        List<FieldError> fieldErrors = exception.getFieldErrors();

        for(FieldError error:exception.getFieldErrors()){
            errors.put(error.getField(), error.getDefaultMessage());
        }
        return errors;
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<FailureResponse> RunTimeHandler(RuntimeException exception){

        FailureResponse failureResponse = new FailureResponse();

        failureResponse.setData(null);
        failureResponse.setError(true);
        failureResponse.setHttpStatus(HttpStatus.CONFLICT);
        failureResponse.setMessage(exception.getMessage());

        return ResponseEntity.status(failureResponse.getHttpStatus()).body(failureResponse);
    }

}
