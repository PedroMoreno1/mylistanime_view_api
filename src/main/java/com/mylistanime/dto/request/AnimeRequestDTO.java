package com.mylistanime.dto.request;

import com.mylistanime.enums.Estado;
import com.mylistanime.enums.Tipo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class AnimeRequestDTO {

    @NotBlank(message = "El titulo no puede estar vacío")
    @Size(max = 100, message = "El titulo no debe superar los 100 caracteres")
    private String titulo;

    @NotNull(message = "El estado es obligatorio")
    private Estado estado;
    @NotNull(message = "El tipo es obligatorio")
    private Tipo tipo;
    @NotNull(message = "El genero es obligatorio")
    private int generoId;

    @NotNull(message = "La fecha de inicio es obligatoria")
    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    private String notas;
    @Pattern(regexp = "^(https?|ftp)://[^\\s/$.?#].[^\\s]*$", message = "El link debe ser una URL válida")
    private String link;
    private String imgUrl;

}
