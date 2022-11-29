package com.example.movie.movie.service;

import com.example.movie.movie.model.Movie;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class MovieSpecifiction {
    public static Specification<Movie> containName(String name) {
        return ((movieRoot, cq, cb) -> {
            return cb.like(cb.lower(movieRoot.get("name")), "%" + name + "%");
        });
    }

    public static Specification<Movie> hasCreateYear(Integer createYear) {
        return ((movieRoot, cq, cb) -> {
            return cb.equal(movieRoot.get("createYear"), createYear);
        });
    }

    public static Specification<Movie> hasWatchMovie(Boolean watchMovie) {
        return ((movieRoot, cq, cb) -> {
            return cb.equal(movieRoot.get("watchMovie"), watchMovie);
        });
    }

    public static Specification<Movie> hasWatchLater(Boolean watchLater) {
        return ((movieRoot, cq, cb) -> {
            return cb.equal(movieRoot.get("watchLater"), watchLater);
        });
    }
}
