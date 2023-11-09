package com.gr.films.service;

import com.gr.films.model.Actor;
import com.gr.films.repository.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActorService {

    // @Autowired
    ActorRepository actorRepository;

    @Autowired
    public ActorService(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    public List<Actor> getAllActors() {
        return actorRepository.findAll();
    }

    public List<Actor> getActorByFirstName(String name) {
        return actorRepository.findByFirstName(name);
    }

    public Actor getActorById(Long actorId) {;
        return actorRepository.findByActorId(actorId);
    }
}
