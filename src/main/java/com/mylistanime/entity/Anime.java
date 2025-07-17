package com.mylistanime.entity;

import com.mylistanime.enums.Estado;
import com.mylistanime.enums.Tipo;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor @Builder
@Entity
@Table(name = "anime")
public class Anime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "anime_id")
    private Long animeId;

    @Column(nullable = false)
    private String titulo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Estado estado;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Tipo tipo;

    @Column(nullable = false, name = "fecha_inicio")
    private LocalDate fechaInicio;
    @Column(nullable = false, name = "fecha_fin")
    private LocalDate fechaFin;

    //Para notas largas, usado solo en String o byte[]
    @Lob
    private String notas;

    @Column(length = 512)
    private String link;

    @Column(name = "img_url",length = 512)
    private String imgUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "genero_id", nullable = false)
    private Genero genero;

}

