package com.mylistanime.mapper;

import com.mylistanime.dto.request.AnimeRequestDTO;
import com.mylistanime.dto.response.AnimeResponseDTO;
import com.mylistanime.entity.Anime;
import com.mylistanime.entity.Genero;
import org.springframework.stereotype.Component;

/**
 * Se usa component: mas flexibilidad, permite inyectar otros beans, test facil
 * con mockito.
 * Spring lo detecta como beean reutilizable.
 * Ya no se agrega Static al metodo.
 * Los metodos serian STATIC si no necesitara inyecciones o estado.
 */

@Component
public class AnimeMapper {

    public Anime convertToEntity(AnimeRequestDTO requestDTO, Genero genero){
        return Anime.builder()
                .titulo(requestDTO.getTitulo())
                .estado(requestDTO.getEstado())
                .tipo(requestDTO.getTipo())
                .genero(genero)
                .fechaInicio(requestDTO.getFechaInicio())
                .fechaFin(requestDTO.getFechaFin())
                .notas(requestDTO.getNotas())
                .link(requestDTO.getLink())
                .imgUrl(requestDTO.getImgUrl())
                .build();
    }

    public AnimeResponseDTO convertToDto(Anime anime){
        return AnimeResponseDTO.builder()
                .id(anime.getAnimeId())
                .titulo(anime.getTitulo())
                .estado(anime.getEstado())
                .tipo(anime.getTipo())
                .generoNombre(anime.getGenero().getNombre())
                .fechaInicio(anime.getFechaInicio())
                .fechaFin(anime.getFechaFin())
                .notas(anime.getNotas())
                .link(anime.getLink())
                .imgUrl(anime.getImgUrl())
                .build();
    }
}
