package com.example.movie.movie.repository;

import com.example.movie.movie.model.Movie;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface MovieRepository extends JpaRepository<Movie, Long>, MovieCustomRepository, JpaSpecificationExecutor<Movie> {

    @Query("SELECT m FROM movies m WHERE m.id=?1")
    Optional<Movie> findByMovieId(Long id);

    List<Movie> findByNameContainingIgnoreCaseAndCreateYearAndWatchMovieAndWatchLater(String name, Integer createYear, Boolean watchMovie, Boolean watchLater, Pageable pageable);
}
