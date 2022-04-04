package com.socialnetwork.demo.exceptions.badRequest;

public abstract class BadRequestException extends RuntimeException{
public BadRequestException(String message){
        super(message);
    }

}
