package com.workintech.d1.service;


import com.workintech.d1.entity.Movie;

import java.util.List;

public interface MovieService {
    List<Movie> findAll();

    Movie findById(long id);

    Movie save(Movie movie);

    Movie delete(long id);
}
