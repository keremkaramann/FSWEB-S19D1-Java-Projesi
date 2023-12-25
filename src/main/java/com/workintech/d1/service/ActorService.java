package com.workintech.d1.service;

import com.workintech.d1.entity.Actor;

import java.util.List;

public interface ActorService {
    List<Actor> findAll();
    Actor findById(long id);
    Actor save(Actor actor);
    Actor delete(long id);
}
