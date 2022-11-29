package com.example.movie.movie.repository;

import com.example.movie.movie.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MovieCustomRepositoryImpl implements MovieCustomRepository {
    @Autowired
    private EntityManager entityManager;

    public MovieCustomRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Movie> findByNameContainingIgnoreCaseAndCreateYearAndWatchMovieAndWatchLater(String name,
                                                                                             Integer createYear,
                                                                                             Boolean watchMovie,
                                                                                             Boolean watchLater,
                                                                                             Pageable pageable
    ) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Movie> cq = cb.createQuery(Movie.class);
        Root<Movie> movieRoot = cq.from(Movie.class);
        List<Predicate> predicates = new ArrayList<>();
        if (!name.isEmpty()) {
            predicates.add(cb.like(cb.lower(movieRoot.get("name")), "%" + name + "%"));
        }
        if (createYear != null) {
            predicates.add(cb.equal(movieRoot.get("createYear"), createYear));
        }
        if (watchMovie != null) {
            predicates.add(cb.equal(movieRoot.get("watchMovie"), watchMovie));
        }
        if (watchLater != null) {
            predicates.add(cb.equal(movieRoot.get("watchLater"), watchLater));
        }
        cq.where(predicates.toArray(new Predicate[0]));

        return entityManager.createQuery(cq).getResultList();
    }
}
