package com.mylistanime.service;

import com.mylistanime.dto.request.AnimeRequestDTO;
import com.mylistanime.dto.response.AnimeResponseDTO;
import com.mylistanime.entity.Anime;
import com.mylistanime.entity.Genero;
import com.mylistanime.mapper.AnimeMapper;
import com.mylistanime.repository.AnimeRepository;
import com.mylistanime.repository.GeneroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AnimeServiceImpl implements AnimeService {

    private final AnimeRepository animeRepository;
    private final GeneroRepository generoRepository;
    private final AnimeMapper animeMapper;

    @Override
    public AnimeResponseDTO crearAnime(AnimeRequestDTO dto) {

        //Obtenemos el IdGenero de la Request, validamos si existe o no.
        Genero genero = generoRepository.findById(dto.getGeneroId())
                .orElseThrow(() -> new IllegalArgumentException("ID invalido"));

        //Con el mapper convertimos a una Entidad, le pasamos la Request
        // y el genero.
        Anime animeObj = animeMapper.convertToEntity(dto, genero);

        //Guardamos en la bd
        Anime animeNuevo = animeRepository.save(animeObj);

        //Ahora convertimos a Dto para el Response
        return animeMapper.convertToDto(animeNuevo);
    }

    /**
     * Para evitar hacerlo varias veces mejor creo un metodo privado y asi evitar codigo repetitivo.
     */
    private Anime existsAnimeById(Long id){

        return animeRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Anime con ID:" +id+" no encontrado"));
    }

    @Override
    public AnimeResponseDTO obtenerAnimePorId(Long id) {

        Anime animeExists = existsAnimeById(id);
        return animeMapper.convertToDto(animeExists);

    }

    @Override
    public List<AnimeResponseDTO> listarAnimes() {
        return animeRepository.findAll()
                .stream()
                .map(animeMapper::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public AnimeResponseDTO actualizarAnime(Long id, AnimeRequestDTO dto) {

        Anime animeExists = existsAnimeById(id);

        Genero genero = generoRepository.findById(dto.getGeneroId())
                .orElseThrow(() -> new IllegalArgumentException("ID invalido"));

        animeExists.setTitulo(dto.getTitulo());
        animeExists.setEstado(dto.getEstado());
        animeExists.setTipo(dto.getTipo());
        animeExists.setFechaInicio(dto.getFechaInicio());
        animeExists.setFechaFin(dto.getFechaFin());
        animeExists.setNotas(dto.getNotas());
        animeExists.setLink(dto.getLink());
        animeExists.setImgUrl(dto.getImgUrl());
        animeExists.setGenero(genero);

        Anime animeUpdate = animeRepository.save(animeExists);

        return animeMapper.convertToDto(animeUpdate);
    }

    @Override
    public void eliminarAnime(Long id) {

        if (!animeRepository.existsById(id)){
            throw new IllegalArgumentException("Anime no encontrado con ID: " + id)
        }
        animeRepository.deleteById(id);
    }
}
