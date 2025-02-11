package com.intern.movieApi.service;

import com.intern.movieApi.entity.Movie;
import com.intern.movieApi.exceptions.ResourceNotFoundException;
import com.intern.movieApi.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {

    @Autowired
    private final MovieRepository movieRepository;

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public Movie getMovieById(Long id) {
        return movieRepository.findById(id).
                orElseThrow(()->new ResourceNotFoundException(
                        "Movie not found with id: " + id));
    }

    public Movie createMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    public Movie updateMovie(Long id, Movie movieDetails) {
        Movie movie =getMovieById(id);
        movie.setTitle(movie.getTitle());
        movie.setDirector(movieDetails.getDirector());
        movie.setGenre(movieDetails.getGenre());
        movie.setReleaseYear(movieDetails.getReleaseYear());
        movie.setImdbRating(movieDetails.getImdbRating());
        return movieRepository.save(movie);
    }

    public void deleteMovie(Long id) {
        Movie movie = getMovieById(id);
        movieRepository.delete(movie);
    }
}
