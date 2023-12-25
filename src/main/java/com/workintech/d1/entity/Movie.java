package com.workintech.d1.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "movie", schema = "fsweb")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    @NotNull
    @Size(min=2,max=45,message = ("Name size must be between 2 and 45"))
    private String name;

    @NotNull
    @Size(min=2,max=45,message = ("Name size must be between 2 and 45"))
    @Column(name = "directorName")
    private String directorName;

    @Column(name = "rating")
    @NotNull
    private int rating;

    @NotNull
    @Column(name = "release_date")
    private LocalDate releaseDate;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.REFRESH, CascadeType.PERSIST})
    @JoinTable(name = "actor_movie_junction", schema = "fsweb",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id"))
    private List<Actor> actorList;

    public void addActor(Actor actor) {
        if (actorList == null) {
            actorList = new ArrayList<>();
        }
        actorList.add(actor);
    }
}
