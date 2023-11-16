package com.gr.films.service;

import com.gr.films.exception.BadRequestException;
import com.gr.films.exception.NotFoundException;
import com.gr.films.model.Movie;
import com.gr.films.repository.MovieRepository;
import com.gr.films.response.ApiError;
import com.gr.films.response.ResponseHandler;
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
    Movie movie;
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
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public Movie getMovieById(Long movieId) {
        if (movieRepository.findById(movieId).isEmpty()) {
            throw new NotFoundException("Couldn't find a movie with the id: " + movieId);
        }
        return movieRepository.findById(movieId).get();
    }

    public ResponseEntity<List<Movie>> getMoviesByReleaseYear(int year) {
        listOfMovies = movieRepository.findByReleaseYear(year);

        if (listOfMovies.isEmpty()) {
            throw new NotFoundException("Couldn't find any movies released in: " + year);
        }

        return new ResponseEntity<>(listOfMovies, HttpStatus.OK);
    }

    public ResponseEntity<Object> addMovie(Movie movie) {
        if (movie.getId() == null) {
            throw new BadRequestException("Id can't be empty");
        }

        movieRepository.save(movie);

        return new ResponseEntity<>("Created " + movie.getTitle(), HttpStatus.OK);
    }

    public ResponseEntity<Object> updateMovie(Movie movie, Long id) {
        Movie movieInDatabase = movieRepository.findByMovieId(id);

        if (movieInDatabase != null) {
            if (movie.getTitle().isEmpty()) {
                throw new BadRequestException("You really shouldn't forget about the title of the movie.");
            }
            movieInDatabase.setTitle(movie.getTitle());
            movieInDatabase.setWriter(movie.getWriter());
            movieInDatabase.setDirector(movie.getDirector());
            movieInDatabase.setGenre(movie.getGenre());
            movieInDatabase.setAwards(movie.getAwards());
            movieInDatabase.setReleaseYear(movie.getReleaseYear());
            movieRepository.save(movieInDatabase);

            return new ResponseEntity<>("Updated " + movieInDatabase.getTitle(), HttpStatus.OK);
        }
        else {
            throw new NotFoundException("There is no movie with the id: " + id);
        }
    }

    public ResponseEntity<Object> deleteMovie(Long id) {
        Movie movieInDatabase = movieRepository.findByMovieId(id);

        if (movieInDatabase != null) {
            movieRepository.deleteById(id);
            return new ResponseEntity<>("Deleted " + movieInDatabase.getTitle(), HttpStatus.OK);
        }
        else {
            throw new NotFoundException("There is no movie with the id: " + id);
        }
    }
}
