package com.example.movie.model;

import com.example.movie.model.tables.PersonMovie;
import com.example.movie.util.LocalDateAdapter;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@XmlType(propOrder = {
        "id",
        "title",
        "description",
        "releaseDate",
        "country",
        "persons"
})
@XmlRootElement
@Entity
@Table(name = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private int id;

    @Column(name = "title", nullable = false, unique = true)
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @OneToMany(mappedBy = "person")
    private Set<PersonMovie> persons = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<PersonMovie> getPersons() {
        return persons;
    }

    public void setPersons(Set<PersonMovie> persons) {
        this.persons = persons;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    // Annotation to marshall/unmarshall the date to xml format
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Movie(int id, String title, String description, LocalDate releaseDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.releaseDate = releaseDate;
    }

    public Movie() {
    }
}
