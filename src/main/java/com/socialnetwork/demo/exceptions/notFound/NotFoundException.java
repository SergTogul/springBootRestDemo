package com.socialnetwork.demo.exceptions.notFound;

public abstract class NotFoundException extends RuntimeException{
public NotFoundException(String message){
        super(message);
    }

}
