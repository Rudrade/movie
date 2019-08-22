package com.example.movie.model;

import com.example.movie.model.tables.PersonMovie;
import com.example.movie.model.tables.PersonOccupation;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@XmlType(propOrder = {
        "id",
        "firstName",
        "lastName",
        "birthDate",
        "movies",
        "country",
        "occupations"
})
@Entity
@Table(name = "person")
@XmlRootElement
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private int id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @OneToMany(mappedBy = "person")
    private Set<PersonOccupation> occupations = new HashSet<>();

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;

    @OneToMany(mappedBy = "movie")
    private Set<PersonMovie> movies = new HashSet<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Set<PersonMovie> getMovies() {
        return movies;
    }

    public void setMovies(Set<PersonMovie> movies) {
        this.movies = movies;
    }

    public Set<PersonOccupation> getOccupations() {
        return occupations;
    }

    public void setOccupations(Set<PersonOccupation> occupations) {
        this.occupations = occupations;
    }
}
