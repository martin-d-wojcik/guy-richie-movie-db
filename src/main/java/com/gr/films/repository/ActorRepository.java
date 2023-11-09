package com.gr.films.repository;

import com.gr.films.model.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface ActorRepository extends JpaRepository<Actor, Long> {

    @Query(value="SELECT * FROM actor WHERE first_name = :actorName OR last_name = :actorName", nativeQuery=true)
    List<Actor> findByActorName(String actorName);

    @Query(value="SELECT * FROM actor WHERE id = ?1", nativeQuery=true)
    Actor findByActorId(Long id);
}
