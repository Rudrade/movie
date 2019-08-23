package com.example.movie.model.tables;

import com.example.movie.model.Country;
import com.example.movie.model.Occupation;
import com.example.movie.model.Person;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "person_occupation")
public class PersonOccupation implements Serializable {
    @EmbeddedId
    private PersonOccupationPK id;

    @ManyToOne
    @MapsId("id")
    @JoinColumn(name = "id")
    @Column(name = "person_id")
    private Person person;

    @ManyToOne
    @MapsId("id")
    @JoinColumn(name = "id")
    @Column(name = "occupation_id")
    private Occupation occupation;

    public PersonOccupationPK getId() {
        return id;
    }

    public void setId(PersonOccupationPK id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Occupation getOccupation() {
        return occupation;
    }

    public void setOccupation(Occupation occupation) {
        this.occupation = occupation;
    }
}
