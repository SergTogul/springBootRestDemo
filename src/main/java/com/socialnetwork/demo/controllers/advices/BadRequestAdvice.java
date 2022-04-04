package com.socialnetwork.demo.controllers.advices;

import com.socialnetwork.demo.exceptions.badRequest.BadRequestException;
import com.socialnetwork.demo.exceptions.notFound.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;

@ControllerAdvice
public class BadRequestAdvice {

    @ResponseBody
    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String NotFoundHandler(BadRequestException ex) {
        return ex.getMessage();
    }
}
