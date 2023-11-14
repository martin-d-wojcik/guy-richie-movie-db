package com.gr.films.controller;

import com.gr.films.model.Actor;
import com.gr.films.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/actors")
    public ResponseEntity<List<Actor>> getAllActors() {
        return actorService.getAllActors();
    }

    @GetMapping("/actor/{name}")
    public ResponseEntity<List<Actor>> getActorByActorName(@PathVariable("name") String actorName) {
        return actorService.getActorByName(actorName);
    }

    @GetMapping("/actor/id/{id}")
    public ResponseEntity<Object> getActorById(@PathVariable("id") Long id) {
        ResponseEntity<Object> obj = actorService.getActorById(id);
        return obj;



        // return actorService.getActorById(id);
    }
}
