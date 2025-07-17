package com.mylistanime.repository;

import com.mylistanime.entity.Anime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimeRepository extends JpaRepository<Anime, Long> {
}
