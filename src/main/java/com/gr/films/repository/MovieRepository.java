package com.gr.films.repository;

import com.gr.films.model.Actor;
import com.gr.films.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query(value="SELECT * FROM movie WHERE id = ?1", nativeQuery=true)
    Movie findByMovieId(Long id);

    @Query(value = "SELECT * FROM movie WHERE release_year = ?1", nativeQuery = true)
    List<Movie> findByReleaseYear(int year);

    @Query(value = "SELECT first_name, last_name " +
            "FROM actor " +
            "INNER JOIN movie ON actor.movie_id = movie.id " +
            "WHERE movie.id = ?1", nativeQuery = true)
    List<Actor> getActorsByMovieId(Long movieId);
}
