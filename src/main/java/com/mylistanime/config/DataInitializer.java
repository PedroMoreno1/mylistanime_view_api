package com.mylistanime.config;

import com.mylistanime.entity.Genero;
import com.mylistanime.repository.GeneroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final GeneroRepository generoRepository;

    @Override
    public void run(String... args) throws Exception {
        if (generoRepository.count() == 0){
            generoRepository.save(new Genero("Shonen", "Batallas con protagonistas jovenes"));
            generoRepository.save(new Genero("Isekai", "Viajes a mundos alternos"));
        }
    }
}
