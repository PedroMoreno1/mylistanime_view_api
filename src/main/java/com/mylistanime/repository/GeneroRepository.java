package com.mylistanime.repository;

import com.mylistanime.entity.Genero;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GeneroRepository extends JpaRepository<Genero, Integer> {

    Optional<Genero> findByNombreIgnoreCase(String nombre);
}
