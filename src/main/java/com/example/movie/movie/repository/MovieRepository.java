package com.example.movie.movie.repository;

import com.example.movie.movie.model.Movie;
import com.example.movie.movie.model.MovieDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie,Long> {

    @Query("SELECT m FROM movie m WHERE m.movieId=?1")
    Optional<Movie> findByMovieId(Long movieId);
    @Query("SELECT m FROM movie m JOIN director ON movie.movieId=director.movie")
    MovieDto allInformation();
}
