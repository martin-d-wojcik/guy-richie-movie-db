package com.gr.films.controller;

import com.gr.films.model.Actor;
import com.gr.films.repository.ActorRepository;
import com.gr.films.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/v1")
public class ActorController {

    // @Autowired
    private final ActorService actorService;

    @Autowired
    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    /* @Autowired
    ActorRepository actorRepository;

    public ActorController(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }
     */

    @GetMapping("/actors")
    public List<Actor> getAllActors() {
        return actorService.getAllActors();
    }

    @GetMapping("/actor/{name}")
    public List<Actor> getActorByFirstName(@PathVariable("name") String firstName) {
        return actorService.getActorByFirstName(firstName);
    }

    @GetMapping("/actor/id/{id}")
    public Actor getActorById(@PathVariable("id") Long id) {
        return actorService.getActorById(id);
    }
}
