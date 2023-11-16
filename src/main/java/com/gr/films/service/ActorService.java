package com.gr.films.service;

import com.gr.films.exception.NotFoundException;
import com.gr.films.model.Actor;
import com.gr.films.repository.ActorRepository;
import com.gr.films.response.ApiError;
import com.gr.films.response.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ActorService {

    // @Autowired
    ActorRepository actorRepository;
    List<Actor> listOfActors;
    Actor actor;
    ApiError apiError;

    @Autowired
    public ActorService(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
        listOfActors = new ArrayList<Actor>();
    }

    public ResponseEntity<List<Actor>> getAllActors() {
        try {
            listOfActors = actorRepository.findAll();

            if (listOfActors.isEmpty()) {
                return new ResponseEntity<>(listOfActors, HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(listOfActors, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<Actor>> getActorByName(String actorName) {
        listOfActors = actorRepository.findByActorName(actorName);

        if (listOfActors.isEmpty()) {
            throw new NotFoundException("Couldn't find any actor with the name " + actorName);
        }

        return new ResponseEntity<>(listOfActors, HttpStatus.OK);
    }

    public Actor getActorById(Long actorId) {
        if (actorRepository.findById(actorId).isEmpty()) {
            throw new NotFoundException("Couldn't find actor with id: " + actorId);
        }

        return actorRepository.findById(actorId).get();
    }

    public String addActor(Actor actor) {
        actorRepository.save(actor);

        return "Created";
    }
}
