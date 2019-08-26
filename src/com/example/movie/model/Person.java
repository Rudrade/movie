package com.example.movie.model;

import com.example.movie.util.LocalDateAdapter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@XmlType(propOrder = {
        "id",
        "firstName",
        "lastName",
        "birthDate",
        "country",
        "occupations",
        "movies"
})
@Entity
@Table(name = "person")
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
@DynamicUpdate
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    @XmlElement
    private int id;

    @XmlElement
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @XmlElement
    @Column(name = "last_name")
    private String lastName;

    @XmlElement
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "person_occupation",
                joinColumns = {@JoinColumn(name="person_id", referencedColumnName="id")},
                inverseJoinColumns = {@JoinColumn(name="occupation_id", referencedColumnName="id")})
    private Set<Occupation> occupations;

    @XmlElement
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @XmlElement
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Country.class)
    private Country country;

    @ManyToMany(mappedBy = "persons", fetch = FetchType.EAGER)
    @XmlElement
    private Set<Movie> movies = new HashSet<>();

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

    public Set<Occupation> getOccupations() {
        return occupations;
    }

    public void setOccupations(Set<Occupation> occupations) {
        this.occupations = occupations;
    }

    @XmlTransient
    public Set<Movie> getMovies() {
        return movies;
    }

    public void setMovies(Set<Movie> movies) {
        this.movies = movies;
    }
}
