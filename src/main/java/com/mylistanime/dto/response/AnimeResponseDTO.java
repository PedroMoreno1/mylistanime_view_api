package com.mylistanime.dto.response;

import com.mylistanime.enums.Estado;
import com.mylistanime.enums.Tipo;
import lombok.*;

import java.time.LocalDate;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class AnimeResponseDTO {

    private Long id;
    private String titulo;
    private Estado estado;
    private Tipo tipo;
    private String generoNombre; // Mostramos solo el nombre del género

    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    private String notas;
    private String link;
    private String imgUrl;
}
