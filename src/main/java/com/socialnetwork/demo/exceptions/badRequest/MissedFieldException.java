package com.socialnetwork.demo.exceptions.badRequest;


public class MissedFieldException extends BadRequestException {
    final private static String ERROR_MESSAGE_TEMPLATE = "Field '%s' is required \n";

    public MissedFieldException(String fieldName){
        super(String.format(ERROR_MESSAGE_TEMPLATE, fieldName));
    }
}
