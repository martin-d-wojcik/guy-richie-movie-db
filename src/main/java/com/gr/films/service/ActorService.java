package com.gr.films.service;

import com.gr.films.exception.BadRequestException;
import com.gr.films.exception.NotFoundException;
import com.gr.films.model.Actor;
import com.gr.films.model.Movie;
import com.gr.films.repository.ActorRepository;
import com.gr.films.repository.MovieRepository;
import com.gr.films.response.ApiError;
import com.gr.films.response.ResponseHandler;
import org.json.JSONArray;
import org.json.JSONML;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ActorService {

    // @Autowired
    ActorRepository actorRepository;
    MovieRepository movieRepository;
    List<Actor> listOfActors;
    Actor actor;
    ApiError apiError;

    @Autowired
    public ActorService(ActorRepository actorRepository, MovieRepository movieRepository) {
        this.actorRepository = actorRepository;
        this.movieRepository = movieRepository;
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
            throw new NotFoundException("Not one actor found.");
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

    public ResponseEntity<Object> addActor(Actor actor) {
        Actor actorInDb = actorRepository.findByActorId(actor.getId());

        // Make sure the id doesn't already exist in the database
        if (actorInDb != null) {
            throw new BadRequestException("There is already an actor in the database with the id: " +
                    actor.getId());
        } else if (actor.getId() == null) {
            // throw exception if there's no id in the request body
            throw new BadRequestException("Id can't be empty");
        }

        // Make sure the there is a movie with the provided movie id
        Movie movieInDb = movieRepository.findByMovieId(actor.getMovieId());
        if (movieInDb == null) {
            throw new BadRequestException("There's no movie in the database with the movie id: " +
                    actor.getMovieId());
        }

        actorRepository.save(actor);
        
        return new ResponseEntity<>("Created " + actor.getFirstName() + " " + actor.getLastName()
                , HttpStatus.CREATED);
    }

    public ResponseEntity<Object> updateActor(Long id, Actor actor) {
        Actor actorInDatabase = actorRepository.findByActorId(id);

        if (actorInDatabase == null) {
            throw new NotFoundException("There's no actor in the database with the id: " + id);
        }

        Movie movieInDb = movieRepository.findByMovieId(actor.getMovieId());
        if (movieInDb == null) {
            throw new BadRequestException("There's no movie in the database with the movie id: " +
                    actor.getMovieId());
        }

        actorRepository.save(actor);

        return new ResponseEntity<>("Updated.", HttpStatus.OK);
    }

    public ResponseEntity<Object> deleteActor(Long id) {
        Actor actorInDatabase = actorRepository.findByActorId(id);

        if (actorInDatabase != null) {
            actorRepository.deleteById(id);
            return new ResponseEntity<>("Deleted " + actorInDatabase.getFirstName() +
                    " " + actorInDatabase.getLastName(), HttpStatus.OK);
        } else {
            throw new NotFoundException("There is no actor with the id: " + id);
        }
    }

    public ResponseEntity<Object> getActorsByMovieId(Long movieId) {
        // Get the movie title
        String title = movieRepository.findByMovieId(movieId).getTitle();
        if (title.isEmpty()) {
            throw new NotFoundException("Couldn't find aby movie with the movie id " + movieId);
        }

        // Get the list of actors by movie id
        List<Actor> listOfActors = actorRepository.findActorNameByMovieId(movieId);
        if (listOfActors.isEmpty()) {
            throw new NotFoundException("Couldn't find any actor with the movie id " + movieId);
        }

        // Create new list with only first- and lastname
        List<Object> listOfNames = new ArrayList<>();

        for (Actor actor : listOfActors) {
            Map<String, Object> payload = new HashMap<>();
            payload.put("firstName", actor.getFirstName());
            payload.put("lastName", actor.getLastName());
            listOfNames.add(payload);
        }

        return ResponseHandler.createArrayInBody(title, listOfNames);
    }
}
