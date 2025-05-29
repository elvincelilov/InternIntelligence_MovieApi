package com.intern.movieApi.repository;

import com.intern.movieApi.entity.Movie;
import com.intern.movieApi.enums.Genre;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Primary
public class CustomMovieRepo {
    private EntityManager entityManager;

    public CustomMovieRepo(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Movie> getList(String title, String director, Genre genre,Integer releaseYear) {

        final CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        final CriteriaQuery<Movie> cq = cb.createQuery(Movie.class);
        final Root<Movie> root = cq.from(Movie.class);

        final List<Predicate> predicates = new ArrayList<>();
        if(title != null && !title.isEmpty()) {
            predicates.add(cb.like(root.get("title"), "%" + title + "%"));
        }
        if(director != null && !director.isEmpty()) {
            predicates.add(cb.like(root.get("director"), "%" + director + "%"));
        }
        if(genre != null) {
            predicates.add(cb.equal(root.get("genre"), genre));
        }
        if(releaseYear != null) {
            predicates.add(cb.greaterThanOrEqualTo(root.get("releaseYear"), releaseYear));
        }
        final Predicate predicate = cb.and(predicates.toArray(new Predicate[0]));

        cq.where(predicate);
        cq.select(root);

        return entityManager.createQuery(cq).getResultList();

    }

}
