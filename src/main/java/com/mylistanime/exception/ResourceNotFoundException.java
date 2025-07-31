package com.mylistanime.exception;

/**
 * Excepcion de recurso no encontrado.
 */
public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String message){
        super(message);
    }
}
