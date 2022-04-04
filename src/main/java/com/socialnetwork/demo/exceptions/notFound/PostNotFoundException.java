package com.socialnetwork.demo.exceptions.notFound;

public class PostNotFoundException extends NotFoundException{
    public PostNotFoundException(Long id){
        super("Can't find a post with id=" + id + "\n");
    }
}
