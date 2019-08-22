package com.example.movie.model;

import com.example.movie.model.tables.PersonOccupation;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Occupation")
@XmlRootElement
public class Occupation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private int id;

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    @Column(name = "occupation", nullable = false, unique = true)
    private String occupation;
/*
    @Column(name = "occupation", nullable = false, unique = true)
    @OneToMany(mappedBy = "person")
    private Set<PersonOccupation> occupation = new HashSet<>();

    public Set<PersonOccupation> getOccupation() {
        return occupation;
    }

    public void setOccupation(Set<PersonOccupation> occupation) {
        this.occupation = occupation;
    }*/

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
