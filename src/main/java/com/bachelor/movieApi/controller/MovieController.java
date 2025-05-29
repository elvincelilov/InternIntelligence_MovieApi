package com.bachelor.movieApi.controller;

import com.bachelor.movieApi.entity.Movie;
import com.bachelor.movieApi.enums.Genre;
import com.bachelor.movieApi.repository.CustomMovieRepo;
import com.bachelor.movieApi.repository.MovieRepository;
import com.bachelor.movieApi.service.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    private final MovieService movieService;
    private final MovieRepository movieRepository;
    private final CustomMovieRepo customMovieRepo;

    public MovieController(MovieService movieService, MovieRepository movieRepository, CustomMovieRepo customMovieRepo) {
        this.movieService = movieService;
        this.movieRepository = movieRepository;
        this.customMovieRepo = customMovieRepo;
    }

    @GetMapping
    public ResponseEntity<?> getMovies(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String director,
            @RequestParam(required = false) Genre genre,
            @RequestParam(required = false) Integer releaseYear
    ) {

        if ((title == null || title.isEmpty()) &&
                (director == null || director.isEmpty()) &&
                genre == null &&
                releaseYear == null) {
            return ResponseEntity.ok(movieRepository.findAll());
        }

        // Əks halda custom filterlə axtarış et
        List<Movie> filteredMovies = customMovieRepo.getList(title, director, genre, releaseYear);
        return ResponseEntity.ok(filteredMovies);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Movie>getMovieById(@PathVariable Long id) {
        return ResponseEntity.ok(movieService.getMovieById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<Movie> createMovie(@RequestBody Movie movie) {
        return ResponseEntity.ok(movieService.createMovie(movie));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable Long id, @RequestBody Movie movie) {
        return ResponseEntity.ok(movieService.updateMovie(id, movie));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Movie> deleteMovie(@PathVariable Long id) {
        movieService.deleteMovie(id);
        return ResponseEntity.noContent().build();
    }
}