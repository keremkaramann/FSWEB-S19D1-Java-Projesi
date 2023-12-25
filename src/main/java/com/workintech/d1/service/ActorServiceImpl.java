package com.workintech.d1.service;

import com.workintech.d1.entity.Actor;
import com.workintech.d1.exceptions.GeneralException;
import com.workintech.d1.exceptions.GlobalExceptionHandler;
import com.workintech.d1.repository.ActorRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ActorServiceImpl implements ActorService{

    private final ActorRepository actorRepository;

    @Override
    public List<Actor> findAll() {
        return null;
    }

    @Override
    public Actor findById(long id) {

        Optional<Actor> optionalActor = actorRepository.findById(id);
        if(optionalActor.isPresent()){
            return optionalActor.get();
        }
        throw new GeneralException("Given id is not exist: " + id, HttpStatus.NOT_FOUND );
    }

    @Override
    public Actor save(Actor actor) {
        return actorRepository.save(actor);
    }

    @Override
    public Actor delete(long id) {
        Actor actor = findById(id);
        if(actor!=null){
            actorRepository.delete(actor);
            return actor;
        }
        throw new GeneralException("Given id is not exist: " + id, HttpStatus.NOT_FOUND);
    }
}
