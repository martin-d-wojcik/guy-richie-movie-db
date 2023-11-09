package com.gr.films.repository;

import com.gr.films.model.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface ActorRepository extends JpaRepository<Actor, Long> {

    @Query(value="SELECT * FROM actor WHERE first_name = :firstName", nativeQuery=true)
    List<Actor> findByFirstName(String firstName);

    @Query(value="SELECT * FROM actor WHERE id = ?1", nativeQuery=true)
    Actor findByActorId(Long id);
}
