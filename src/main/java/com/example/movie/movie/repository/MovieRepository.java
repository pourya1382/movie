package com.example.movie.movie.repository;
import com.example.movie.movie.model.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface MovieRepository extends JpaRepository<Movie,Long> {

    @Query("SELECT m FROM movies m WHERE m.id=?1")
    Optional<Movie> findByMovieId(Long id);

    Page<Movie> findByWatchMovieAndNameContainingAndCreateYear(Boolean watchMovie,String name,Integer createYear ,Pageable pageable);
    Page<Movie> findByViewOnAnotherOccasion(boolean viewOnAnotherOccasion,Pageable pageable);
    Page<Movie> findByNameContainingAndCreateYear(String movieName,Integer createYear, Pageable pageable);
    Page<Movie> findByNameContaining(String movieName, Pageable pageable);
    Page<Movie> findByOrderByName(Pageable pageable);
    Page<Movie> findByOrderByNameDesc(Pageable pageable);






//    @Query("SELECT d,m FROM director d INNER JOIN movie m ON d.movieId=m.movieId")
//    MovieDto allInformation();
//    @Query("SELECT m.name,m.name,m.createYear,d.nameDirector,d.family,d.age FROM movie m INNER JOIN director d ON d.movieId=m.movieId")
//    StateDto allInformation(Long movieId);
}
