package com.mylistanime.service;

import com.mylistanime.dto.request.AnimeRequestDTO;
import com.mylistanime.dto.response.AnimeResponseDTO;

import java.util.List;

public interface AnimeService {

    AnimeResponseDTO crearAnime(AnimeRequestDTO dto);
    AnimeResponseDTO obtenerAnimePorId(Long id);
    List<AnimeResponseDTO> listarAnimes();
    AnimeResponseDTO actualizarAnime(Long id, AnimeRequestDTO dto);
    void eliminarAnime(Long id);
}
