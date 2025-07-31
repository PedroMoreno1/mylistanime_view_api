package com.mylistanime.controller;

import com.mylistanime.dto.request.AnimeRequestDTO;
import com.mylistanime.dto.response.AnimeResponseDTO;
import com.mylistanime.entity.Anime;
import com.mylistanime.service.AnimeService;
import com.mylistanime.util.AppSettings;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/animes")
@RequiredArgsConstructor
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class AnimeController {

    private final AnimeService animeService;

    @PostMapping
    public ResponseEntity<AnimeResponseDTO> crearAnime(@Valid @RequestBody AnimeRequestDTO requestDTO){
        AnimeResponseDTO crear = animeService.crearAnime(requestDTO);
        //Armamos la URI (para mostrar la ruta de busqueda del recurso creado)
        URI location = URI.create("/api/animes/" + crear.getId());
        //Devolvemos 201 created + location + body
        return ResponseEntity.created(location).body(crear);
        //return ResponseEntity.status(HttpStatus.CREATED).body(crear);
    }

    @GetMapping
    public ResponseEntity<List<AnimeResponseDTO>> listarAnimes(){
        List<AnimeResponseDTO> animes = animeService.listarAnimes();
        return ResponseEntity.ok(animes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnimeResponseDTO> obtenerAnimePorId(@PathVariable Long id){

        AnimeResponseDTO animeId = animeService.obtenerAnimePorId(id);

        return ResponseEntity.ok(animeId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AnimeResponseDTO> actualizarAnime(@PathVariable Long id, @Valid @RequestBody AnimeRequestDTO requestDTO){
        AnimeResponseDTO actualizado = animeService.actualizarAnime(id, requestDTO);
        return ResponseEntity.ok(actualizado);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarAnime(@PathVariable Long id){
        animeService.eliminarAnime(id);
        return ResponseEntity.noContent().build();
    }

}
