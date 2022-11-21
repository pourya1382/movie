package com.example.movie.movie.repository;

import com.example.movie.movie.model.Director;
import com.example.movie.movie.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository

public interface DirectorRepository extends JpaRepository<Director,Long> {
@Query("SELECT d FROM director d where d.directorId=?1")
Optional<Director> findByDirectorId(Long directorId);
@Query("SELECT d FROM movie d JOIN director .movie =movie.movieId")
    movieDto allInformation;
}
