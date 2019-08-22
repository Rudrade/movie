package com.example.movie.model.tables;

import com.example.movie.model.Movie;
import com.example.movie.model.Person;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "person_movie")
public class PersonMovie implements Serializable {
    @EmbeddedId
    private PersonMoviePK id;

    @ManyToOne
    @MapsId("personId")
    @JoinColumn(name = "person_id")
    private Person person;

    @ManyToOne
    @MapsId("movie_id")
    @JoinColumn(name = "movie_id")
    private Movie movie;

    public PersonMoviePK getId() {
        return id;
    }

    public void setId(PersonMoviePK id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}
