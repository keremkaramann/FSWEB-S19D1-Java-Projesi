package com.workintech.d1.dto;

import com.workintech.d1.entity.Actor;
import com.workintech.d1.entity.Movie;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
public class MovieActorRequest {
    private Movie movie;
    private List<Actor> actorList;
}
