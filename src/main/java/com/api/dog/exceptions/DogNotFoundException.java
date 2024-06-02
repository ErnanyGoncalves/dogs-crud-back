package com.api.dog.exceptions;

public class DogNotFoundException extends RuntimeException {

    public DogNotFoundException(Object id){
        super("Dog with id "+id+" wasn't found.");
    }
}
