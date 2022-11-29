package com.example.movie.movie.model;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class MovieDto {
    EntityManager entityManager;

    public MovieDto(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    List<Movie> findByNameAndCreateYearAndWatchMovieAndWatchLater(String name,
                                                                  Integer createYear,
                                                                  Boolean watchMovie,
                                                                  Boolean watchLater,
                                                                  Pageable pageable
    ) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Movie> criteriaQuery = criteriaBuilder.createQuery(Movie.class);
        Root<Movie> movie = criteriaQuery.from(Movie.class);
        Predicate movieNamePerdicate = criteriaBuilder.like(criteriaBuilder.lower(movie.get("name")), "%" + name + "%");
        Predicate createYearPerdicate = criteriaBuilder.equal(movie.get("createYear"), createYear);
        Predicate watchMoviePerdicate = criteriaBuilder.equal(movie.get("watchMovie"), watchMovie);
        Predicate watchLaterPerdicate = criteriaBuilder.equal(movie.get("watchLater"), watchLater);
        criteriaQuery.where(movieNamePerdicate, createYearPerdicate, watchMoviePerdicate, watchLaterPerdicate);
        TypedQuery<Movie> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }
}
