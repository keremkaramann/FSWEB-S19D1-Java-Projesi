package com.workintech.d1.controller;

import com.workintech.d1.dto.ActorRequest;
import com.workintech.d1.dto.ActorResponse;

import com.workintech.d1.dto.MovieResponse;
import com.workintech.d1.entity.Actor;
import com.workintech.d1.entity.Movie;

import com.workintech.d1.service.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/movie")
@CrossOrigin("localhost:8080")
public class MovieController {

    private final MovieService movieService;


    @PostMapping("/")
    public MovieResponse save(@Validated @RequestBody ActorRequest movieRequest) {
        List<Movie> movieList = movieRequest.getMovie();
        Actor actor = movieRequest.getActorList();

        for (Movie movie : movieList) {
            movie.addActor(actor);
        }
        Movie savedMovie = movieService.save(movieList.get(0));
        return new MovieResponse(savedMovie.getId(), savedMovie.getName(), savedMovie.getDirectorName(),
                savedMovie.getRating(), savedMovie.getReleaseDate());
    }

    @GetMapping("/{movieId}")
    public MovieResponse find(@PathVariable long movieId) {
        Movie movie = movieService.findById(movieId);

        List<ActorResponse> actorResponses = new ArrayList<>();
        for (Actor actor : movie.getActorList()) {
            actorResponses.add(new ActorResponse(actor.getId(), actor.getFirstName(), actor.getLastName(),
                    actor.getBirthDate(), actor.getMovieList()));
        }

        return new MovieResponse(movie.getId(), movie.getName(), movie.getDirectorName(), movie.getRating(),
                movie.getReleaseDate(), actorResponses);
    }

    @PutMapping("/addActor/{movieId}")
    public MovieResponse addActor(@PathVariable long movieId, @RequestBody Actor actor) {
        Movie movie = movieService.findById(movieId);
        movie.addActor(actor);
        Movie savedMovie = movieService.save(movie);
        return new MovieResponse(savedMovie.getId(), savedMovie.getName(), savedMovie.getDirectorName(),
                savedMovie.getRating(), savedMovie.getReleaseDate());
    }

    @DeleteMapping("/{id}")
    public Movie remove(@PathVariable long id) {
        return movieService.delete(id);
    }

}
