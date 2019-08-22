package com.example.movie.model.tables;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class PersonMoviePK implements Serializable  {
    @Column(name = "person_id")
    private int personId;

    @Column(name = "movie_id")
    private int movieId;
}
