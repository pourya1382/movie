package com.example.movie.movie.service;

import com.example.movie.movie.model.Movie;
import com.example.movie.movie.repository.MovieRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class MovieConfig {
    @Bean
    CommandLineRunner commandLineRunner(MovieRepository movieRepository) {
        movieRepository.deleteAll();
        return args -> {
            Movie GreenMile = new Movie(
                    "GreenMile",
                    "https://www.imdb.com/title/tt0120689/",
                    1999,
                    8.3
            );
            Movie Interstellar = new Movie(
                    "Interstellar",
                    "https://www.imdb.com/title/tt0816692/",
                    2014,
                    8.6
            );
//            Director ChristopherNolan = new Director(
//                    "christopher",
//                    "Nolan",
//                    52
//
//            );
//            Director FrankDarabont = new Director(
//                    "Frank",
//                    "Darabont",
//                    63
//            );
            movieRepository.saveAll(List.of(GreenMile, Interstellar));
//            directorRepository.saveAll(List.of(ChristopherNolan, FrankDarabont));
        };

    }
}
