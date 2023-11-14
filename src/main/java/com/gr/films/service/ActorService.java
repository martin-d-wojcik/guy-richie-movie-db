package com.gr.films.service;

import com.gr.films.model.Actor;
import com.gr.films.repository.ActorRepository;
import com.gr.films.response.ApiError;
import com.gr.films.response.ResponseHandler;
import org.json.JSONObject;
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
        try {
            listOfActors = actorRepository.findAll();

            if (listOfActors.isEmpty()) {
                return new ResponseEntity<>(listOfActors, HttpStatus.NO_CONTENT);
            }

            if (actorName == null) {
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }

            return new ResponseEntity<>(listOfActors, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> getActorById(Long actorId) {
        try {
            actor = actorRepository.findByActorId(actorId);

            if (actor == null) {
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }

            if (actorId == null) {
                return  ResponseHandler.createResponseBody(
                        "Not allowed to parse an empty id.",
                        HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(actor, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
