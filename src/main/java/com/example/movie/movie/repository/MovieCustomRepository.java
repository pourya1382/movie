package com.example.movie.movie.repository;

import com.example.movie.movie.model.Movie;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MovieCustomRepository {
    List<Movie> findByNameContainingIgnoreCaseAndCreateYearAndWatchMovieAndWatchLater(String name,
                                                                                      Integer createYear,
                                                                                      Boolean watchMovie,
                                                                                      Boolean watchLater,
                                                                                      Pageable pageable);
}
