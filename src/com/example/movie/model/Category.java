package com.example.movie.model;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.util.Set;

@Entity
@Table(name = "category")
@XmlRootElement
@XmlType(propOrder = {
        "id",
        "name"
})
@XmlAccessorType(XmlAccessType.NONE)
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @XmlElement
    @Column(name = "id", nullable = false, unique = true)
    private int id;

    @XmlElement
    @Column(name = "name", unique = true)
    private String name;

    @ManyToMany(mappedBy = "categories", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Movie> movies;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Movie> getMovies() {
        return this.movies;
    }

    public void setMovies(Set<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
