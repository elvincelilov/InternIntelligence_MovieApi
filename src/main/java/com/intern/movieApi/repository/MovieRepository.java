package com.intern.movieApi.repository;

import com.intern.movieApi.entity.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query("SELECT m FROM Movie m ORDER BY m.imdbRating DESC")
    List<Movie> findAllSortedByRating();

    Page<Movie> findAll(Pageable pageable);
}
