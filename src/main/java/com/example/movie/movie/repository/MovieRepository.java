package com.example.movie.movie.repository;

import com.example.movie.movie.model.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query("SELECT m FROM movies m WHERE m.id=?1")
    Optional<Movie> findByMovieId(Long id);

//    @Query("SELECT m FROM movies m WHERE  COALESCE(m.name,movies.name) =?1 and COALESCE( m.createYear,movies.createYear)=?1 and COALESCE( m.watchMovie,movies.watchMovie)=?1 and COALESCE( m.watchLater,movies.watchLater)=?1")
//    Page<Movie> findMovieBySearch(String name, Integer createYear, boolean watchMovie, boolean watchLater, Pageable pageable);

    Page<Movie> findByNameContainingIgnoreCaseAndCreateYearAndWatchMovieAndWatchLater(String name,
                                                                                      Integer createYear,
                                                                                      boolean watchMovie,
                                                                                      boolean watchLater,
                                                                                      Pageable pageable
                                                                                      );
//    Page<Movie> findByNameContainingIgnoreCaseAndCreateYearAndWatchMovieAndWatchLaterAndOrderByName(String name,
//                                                                                      Integer createYear,
//                                                                                      boolean watchMovie,
//                                                                                      boolean watchLater,
//                                                                                      Pageable pageable
//    );
//    Page<Movie> findByNameContainingIgnoreCaseAndCreateYearAndWatchMovieAndWatchLaterAndOrderByNameAsc(String name,
//                                                                                                    Integer createYear,
//                                                                                                    boolean watchMovie,
//                                                                                                    boolean watchLater,
//                                                                                                    Pageable pageable
//    );
    Page<Movie> findByNameContainingIgnoreCaseAndWatchMovieAndWatchLater(String name,
                                                                         boolean watchMovie,
                                                                         boolean watchLater,
                                                                         Pageable pageable
    );
//
//    Page<Movie> findByNameContainingIgnoreCaseAndWatchMovieAndWatchLaterAndOrderByCreateYear(String name,
//                                                                         boolean watchMovie,
//                                                                         boolean watchLater,
//                                                                         Pageable pageable
//    );
//    Page<Movie> findByNameContainingIgnoreCaseAndWatchMovieAndWatchLaterAndOrderByCreateYearAsc(String name,
//                                                                                             boolean watchMovie,
//                                                                                             boolean watchLater,
//                                                                                             Pageable pageable
//    );
    Page<Movie> findByOrderByName(Pageable pageable);
    Page<Movie> findByOrderByNameDesc(Pageable pageable);

    Page<Movie> findByNameContainingIgnoreCaseAndWatchLater(String movieName, boolean watchLaterBoolean, Pageable pageable);

    Page<Movie> findByNameContainingIgnoreCase(String movieName, Pageable pageable);

    Page<Movie> findByNameContainingIgnoreCaseAndWatchMovie(String movieName, boolean viewStatusBoolean, Pageable pageable);

    Page<Movie> findByNameContainingIgnoreCaseAndCreateYear(String movieName, int createYear, Pageable pageable);

    Page<Movie> findByNameContainingIgnoreCaseAndCreateYearAndWatchLater(String movieName, int createYear, boolean watchLaterBoolean, Pageable pageable);

    Page<Movie> findByNameContainingIgnoreCaseAndCreateYearAndWatchMovie(String movieName, int createYear, boolean viewStatusBoolean, Pageable pageable);

//    Page<Movie> findByNameContainingIgnoreCaseAndWatchMovieAndWatchLater(String movieName, boolean viewStatusBoolean, boolean watchLaterBoolean, Pageable pageable, Sort name);
//
//    Page<Movie> findByWatchMovieAndWatchLaterAndOrderByName( boolean viewStatusBoolean, boolean watchLaterBoolean, Pageable pageable);
//
//    Page<Movie> findByWatchMovieAndWatchLaterAndOrderByNameAsc( boolean viewStatusBoolean, boolean watchLaterBoolean, Pageable pageable);
//
//    Page<Movie> findByCreateYearAndWatchMovieAndWatchLaterAndOrderByNameAsc(int createYear, boolean viewStatusBoolean, boolean watchLaterBoolean, Pageable pageable);
//
//    Page<Movie> findByOrderByNameAndCreateYearAndWatchMovieAndWatchLater(int createYear, boolean viewStatusBoolean, boolean watchLaterBoolean, Pageable pageable);
//
//    Page<Movie> findByOrderByNameAndWatchMovieAndWatchLater(boolean viewStatusBoolean, boolean watchLaterBoolean, Pageable pageable);
//
//    Page<Movie> findByOrderByNameAscAndWatchMovieAndWatchLater(boolean viewStatusBoolean, boolean watchLaterBoolean, Pageable pageable);
//
//    Page<Movie> findByNameOrderByCreateYearAndContainingIgnoreCaseAndWatchMovieAndWatchLater(String movieName, boolean viewStatusBoolean, boolean watchLaterBoolean, Pageable pageable);
//
//    Page<Movie> findByOrderByCreateYearAscAndNameContainingIgnoreCaseAndWatchMovieAndWatchLater(String movieName, boolean viewStatusBoolean, boolean watchLaterBoolean, Pageable pageable);
//
//    Page<Movie> findByOrderByNameAscAndCreateYearAndWatchMovieAndWatchLater(int createYear, boolean viewStatusBoolean, boolean watchLaterBoolean, Pageable pageable);
//

//    @Query("SELECT d,m FROM director d INNER JOIN movie m ON d.movieId=m.movieId")
//    MovieDto allInformation();
//    @Query("SELECT m.name,m.name,m.createYear,d.nameDirector,d.family,d.age FROM movie m INNER JOIN director d ON d.movieId=m.movieId")
//    StateDto allInformation(Long movieId);
}
