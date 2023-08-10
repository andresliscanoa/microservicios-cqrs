package com.lliscano.microserviciocommand.advice;

import com.lliscano.commons.advice.RestResponseEntityExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UsersControllerExceptionHandler extends RestResponseEntityExceptionHandler {
    public UsersControllerExceptionHandler() {
        super();
    }
}
