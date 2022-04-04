package com.socialnetwork.demo.exceptions.notFound;

public class UserNotFoundException extends NotFoundException{
    public UserNotFoundException(Long id){
        super("Can't find a user with id=" + id + "\n");
    }
}
