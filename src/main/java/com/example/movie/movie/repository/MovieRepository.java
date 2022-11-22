package com.example.movie.movie.repository;
import com.example.movie.movie.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository

public interface MovieRepository extends JpaRepository<Movie,Long> {

    @Query("SELECT m FROM movies m WHERE m.id=?1")
    List<Movie> findByMovieId(Long id);

//    @Query("SELECT d,m FROM director d INNER JOIN movie m ON d.movieId=m.movieId")
//    MovieDto allInformation();
//    @Query("SELECT m.name,m.name,m.createYear,d.nameDirector,d.family,d.age FROM movie m INNER JOIN director d ON d.movieId=m.movieId")
//    StateDto allInformation(Long movieId);
}
