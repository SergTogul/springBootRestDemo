package com.socialnetwork.demo.exceptions.notFound;

public class UserPostNotFoundException extends NotFoundException{
    public UserPostNotFoundException(Long id){
        super("Can't find any post for user with userId=" + id + "\n");
    }
}
