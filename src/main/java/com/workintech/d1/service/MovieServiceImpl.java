package com.workintech.d1.service;

import com.workintech.d1.entity.Actor;
import com.workintech.d1.entity.Movie;
import com.workintech.d1.exceptions.GeneralException;
import com.workintech.d1.exceptions.GlobalExceptionHandler;
import com.workintech.d1.repository.MovieRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;

    @Override
    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    @Override
    public Movie findById(long id) {
        Optional<Movie> optionalMovie = movieRepository.findById(id);
        if (optionalMovie.isPresent()) {
            return optionalMovie.get();
        }
        throw new GeneralException("Given id is not found:" + id, HttpStatus.NOT_FOUND);
    }

    @Override
    public Movie save(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public Movie delete(long id) {
        Movie movie = findById(id);
        if (movie != null) {
            movieRepository.delete(movie);
            return movie;
        }
        throw new GeneralException("Given id is not found:" + id, HttpStatus.NOT_FOUND);
    }
}
