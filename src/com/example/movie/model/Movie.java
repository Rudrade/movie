package com.example.movie.model;

import com.example.movie.util.LocalDateAdapter;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;
import java.util.Set;

@XmlType(propOrder = {
        "id",
        "title",
        "description",
        "releaseDate",
        "country",
        "persons",
        "categories"
})
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
@Entity
@Table(name = "movies")
public class Movie {
    @Id
    @XmlElement
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private int id;

    @XmlElement
    @Column(name = "title", nullable = false, unique = true)
    private String title;

    @XmlElement
    @Column(name = "description")
    private String description;

    @Column(name = "release_date")
    // Annotation to marshall/unmarshall the date to xml format
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    @XmlElement
    private LocalDate releaseDate;

    @XmlElement
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "person_movie",
                joinColumns = {@JoinColumn(name = "movie_id", referencedColumnName = "id")},
                inverseJoinColumns = {@JoinColumn(name = "person_id", referencedColumnName = "id")})
    private Set<Person> persons;

    @XmlElement
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Country.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "country_id")
    private Country country;

    @XmlElement
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "movie_category",
                joinColumns = {@JoinColumn(name = "movie_id", referencedColumnName = "id")},
                inverseJoinColumns = {@JoinColumn(name = "category_id", referencedColumnName = "id")})
    private Set<Category> categories;

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

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    @XmlTransient
    public Set<Person> getPersons() {
        return persons;
    }

    public void setPersons(Set<Person> persons) {
        this.persons = persons;
    }

    @XmlTransient
    public Set<Category> getCategories() {
        return this.categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", releaseDate=" + releaseDate +
                ", persons=" + persons +
                ", country=" + country.getName() +
                ", categories=" + categories +
                '}';
    }
}
