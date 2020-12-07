package com.crypto.backend.advice;

import com.crypto.backend.dto.BaseResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/*
   Handles all the server errors occuring in the application.
   Although this just returns a generic server error message,
   this class can be extended to have additional handlers to
   handle specific exceptions.
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleException(Exception ex) {
        BaseResponse response = new BaseResponse(false, "Sever Error Occured");
        return buildResponseEntity(response);
    }

    private ResponseEntity<Object> buildResponseEntity(BaseResponse baseMessage) {
        return new ResponseEntity<>(baseMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
