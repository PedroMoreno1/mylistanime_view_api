package com.mylistanime.exception;

import com.mylistanime.exception.error.ErrorBuilder;
import com.mylistanime.exception.error.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.MethodArgumentNotValidException;
import java.lang.Exception;

import java.util.Map;

/**
 * Controlador global de excepciones.
 * - @RestControllerAdvice usado para apisRest.
 * - Incluye @ResponseBody implicito, el mensaje se devuelve como JSON a diferencia
 *   de @ControllerAdvice que sirve para redirigir a una vista si no se usa @ResponseBody.
 */

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final ErrorBuilder errorBuilder;

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFound(ResourceNotFoundException ex, HttpServletRequest request){

        ErrorResponse errorResponse = errorBuilder.build(
                "NOT_FOUND",
                ex.getMessage(),
                request,
                HttpStatus.NOT_FOUND,
                ex
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handlerValidationException(MethodArgumentNotValidException ex, HttpServletRequest request){
        //--Obtiene resultado del proceso de validacion(" ex.getBindingResult() "), devuelve errores de validacion campo por campo(" .getFieldErrors() ").
        //--Cada error es un objeto FieldError.
        String mensaje = ex.getBindingResult().getFieldErrors()
                //---Inicia un flujo de procesamiento sobre la lista de errores.
                .stream()
                //---Transforma cada FieldError en un String con este formato.
                .map(e -> e.getField() + ": " + e.getDefaultMessage())
                //---Toma el primer mensaje de error que encontró.
                //Se podrian mostrar todos usando -> collect(Collectors.toList())
                .findFirst()
                //Si no hay un error como tal(seria raro), se muestra un mensaje genérico.
                .orElse("Error de validación");

        Map<String, Object> data = Map.of("ValidationoErrors", mensaje);

        ErrorResponse response = errorBuilder.build(
                "BAD_REQUEST",
                "Al validar se encontro un error, cuidado!",
                request,
                HttpStatus.BAD_REQUEST,
                data
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> hanldeGenericException(Exception ex, HttpServletRequest request){

        ErrorResponse errorResponse = errorBuilder.build(

                "INTERNAL_SERVER_ERROR",
                "Se produjo un error inesperado",
                request,
                HttpStatus.INTERNAL_SERVER_ERROR,
                ex

        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
}
