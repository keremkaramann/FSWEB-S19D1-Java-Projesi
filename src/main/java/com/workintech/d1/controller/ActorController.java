package com.workintech.d1.controller;

import com.workintech.d1.dto.ActorRequest;
import com.workintech.d1.dto.ActorResponse;
import com.workintech.d1.entity.Actor;
import com.workintech.d1.entity.Movie;
import com.workintech.d1.service.ActorService;
import com.workintech.d1.util.Converter;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/actor")
public class ActorController {
    private final ActorService actorService;

    @GetMapping
    public List<ActorResponse> findAll() {
        List<Actor> allActors = actorService.findAll();
        return Converter.actorResponseConvert(allActors);
    }

    @GetMapping("/{id}")
    public ActorResponse findById(@PathVariable long id) {
        return Converter.actorResponseConvert(actorService.findById(id));
    }

    @PostMapping("/")
    public ActorResponse save(@Validated @RequestBody ActorRequest actorRequest) {
        List<Movie> movies = actorRequest.getMovie();
        Actor actor = actorRequest.getActorList();

        for (Movie movie : movies) {
            actor.addMovie(movie);
        }

        actorService.save(actor);
        return Converter.actorCreateResponseConvert(actor);
    }

    @PutMapping("/{id}")
    public ActorResponse update(@RequestBody Actor actor, @PathVariable long id) {
        Actor updateActor = actorService.findById(id);
        actor.setMovieList(updateActor.getMovieList());
        actor.setId(id);
        actorService.save(actor);
        return Converter.actorResponseConvert(actor);
    }

    @DeleteMapping("/{id}")
    public ActorResponse delete(@PathVariable long id) {
        Actor foundActor = actorService.findById(id);
        actorService.delete(id);
        return Converter.actorResponseConvert(foundActor);
    }
}
