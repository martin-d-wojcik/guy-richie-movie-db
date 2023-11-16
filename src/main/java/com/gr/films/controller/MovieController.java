package com.gr.films.controller;

import com.gr.films.model.Movie;
import com.gr.films.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/v1")
public class MovieController {

    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/movies")
    public ResponseEntity<List<Movie>> getAllMovies() {
        return movieService.getAllMovies();
    }

    @GetMapping("/movie/id/{id}")
    public Movie getMovieById(@PathVariable("id") Long id) {
        return movieService.getMovieById(id);
    }


    @GetMapping("movie/year/{year}")
    public ResponseEntity<List<Movie>> getMoviesByReleaseYear(@PathVariable("year") int year) {
        return movieService.getMoviesByReleaseYear(year);
    }

    @PostMapping(value = "/movie/add", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> addMovie(@RequestBody Movie movie) {
        return movieService.addMovie(movie);
    }

    @PutMapping("movie/{id}")
    public ResponseEntity<Object> updateMovie(@PathVariable("id") Long id, @RequestBody Movie movie) {
        return movieService.updateMovie(movie, id);
    }

    @DeleteMapping("movie/{id}")
    public ResponseEntity<Object> deleteMovie(@PathVariable("id") Long id) {
        return movieService.deleteMovie(id);
    }

}
