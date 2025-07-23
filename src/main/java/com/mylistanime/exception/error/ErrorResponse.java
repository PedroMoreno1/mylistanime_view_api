package com.mylistanime.exception.error;

import lombok.Builder;
import lombok.Getter;

/**
 * - Objeto que se devuelve al cliente como respuesta.
 * - Contiene la propiedad "error" que contiene los detalles de error (ErrorDetails)
 * --
 * SE EMPAQUETA Y SE DEVUELVE (3)
 */
@Getter
@Builder
public class ErrorResponse {

    private ErrorDetails error;
}
