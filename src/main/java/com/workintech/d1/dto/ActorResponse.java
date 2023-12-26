package com.workintech.d1.dto;

import com.workintech.d1.entity.Movie;

import java.time.LocalDate;
import java.util.List;

public record ActorResponse(long id , String firstName, String lastName, LocalDate birthDate, List<Movie> movies) {
}
