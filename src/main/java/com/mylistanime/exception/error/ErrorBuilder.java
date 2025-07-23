package com.mylistanime.exception.error;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

/**
 * Encargado de construir el ErrorResponse.
 * para ser utilizadas en controladores, handlers globales, etc.
 * --
 * ARMA EL ERROR (1)
 */

@Component
public class ErrorBuilder {

    //Manejo de formato de fecha
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    //Metodo 1:
    public ErrorResponse build(
            String type,
            String message,
            HttpServletRequest request,
            HttpStatus status,
            Exception ex
    ){
        //Para evitar NullPointer al no tener un mensaje como tal
        String exceptionMessage = Optional.ofNullable(ex.getMessage()).orElse("No message");

        return ErrorResponse.builder()
                .error(ErrorDetails.builder()
                        .type(type)
                        .message(message)
                        .state(status.value())
                        .path(request.getRequestURI())
                        .exceptionTime(LocalDateTime.now().format(DATE_TIME_FORMATTER))
                        .data(Map.of("exception", exceptionMessage))
                        .build())
                .build();
    }

    //Metodo 2:
    public ErrorResponse build(
            String type,
            String message,
            HttpServletRequest request,
            HttpStatus status,
            Map<String, Object> data
    ){
        //Para proteger null en data se crea un map: "datosSeguros"
        Map<String, Object> safeData = Optional.ofNullable(data)
                .orElseGet(Collections::emptyMap);

        return ErrorResponse.builder()
                .error(ErrorDetails.builder()
                        .type(type)
                        .message(message)
                        .state(status.value())
                        .path(request.getRequestURI())
                        .exceptionTime(LocalDateTime.now().format(DATE_TIME_FORMATTER))
                        .data(safeData)
                        .build())
                .build();
    }

}
