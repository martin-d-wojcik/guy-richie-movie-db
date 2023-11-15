package com.gr.films.repository;

import com.gr.films.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query(value="SELECT * FROM movie WHERE id = ?1", nativeQuery=true)
    Movie findByMovieId(Long id);
}
