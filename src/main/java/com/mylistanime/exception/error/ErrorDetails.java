package com.mylistanime.exception.error;

import lombok.Builder;
import lombok.Getter;

import java.util.Map;

/**
 * Estructura de los detalles internos de un error.
 * --
 * DETALLES DEL ERROR (2)
 */
@Getter
@Builder
public class ErrorDetails {

    private String type;
    private String message;
    private int state;
    private String path;
    private String exceptionTime; //directamente se manda la excepcion: 1
    private Map<String, Object> data; //Para adicionar mensajes: 2
}
