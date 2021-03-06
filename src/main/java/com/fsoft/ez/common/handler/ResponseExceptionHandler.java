package com.fsoft.ez.common.handler;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.fsoft.ez.common.exception.NotFoundException;
import com.fsoft.ez.common.model.RestErrorInfo;

@ControllerAdvice
@RestController
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<RestErrorInfo> handleAllExceptions(Exception ex,
        WebRequest request) {
        ex.printStackTrace();
        RestErrorInfo errorDetails = new RestErrorInfo(new Date(),
            ex.getMessage(),
            request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NotFoundException.class)
    public final ResponseEntity<RestErrorInfo> handleUserNotFoundException(NotFoundException ex,
        WebRequest request) {
        RestErrorInfo errorDetails = new RestErrorInfo(new Date(),
            ex.getMessage(),
            request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

//    @Override
//    protected ResponseEntity<Object>
//                    handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
//                        HttpHeaders headers,
//                        HttpStatus status,
//                        WebRequest request) {
//        Map<String, Object> body = new LinkedHashMap<>();
//        body.put("timestamp", new Date());
//        body.put("status", status.value());
//        // Get all errors
//        List<String> errors = ex.getBindingResult().getFieldErrors().stream()
//                        .map(x -> x.getDefaultMessage())
//                        .collect(Collectors.toList());
//
//        body.put("message", errors);
//        return new ResponseEntity<>(body, headers, status);
//    }

}
