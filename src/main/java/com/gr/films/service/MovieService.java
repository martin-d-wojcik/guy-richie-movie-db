package com.gr.films.service;

import com.gr.films.model.Movie;
import com.gr.films.repository.MovieRepository;
import com.gr.films.response.ApiError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {

    MovieRepository movieRepository;
    List<Movie> listOfMovies;

    ApiError apiError;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
        listOfMovies = new ArrayList<Movie>();
    }

    public ResponseEntity<List<Movie>> getAllMovies() {
        try {
            listOfMovies = movieRepository.findAll();
            return new ResponseEntity<>(listOfMovies, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
