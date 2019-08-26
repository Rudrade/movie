package com.example.movie.model;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "occupation")
@XmlRootElement
@XmlType(propOrder = {
        "id",
        "occupation"
})
@XmlAccessorType(XmlAccessType.NONE)
public class Occupation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    @XmlElement
    private int id;

    @XmlElement
    @Column(name = "occupation", nullable = false, unique = true)
    private String occupation;

    @ManyToMany(mappedBy = "occupations")
    private Set<Person> persons = new HashSet<> ();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public Set<Person> getPersons() {
        return persons;
    }

    public void setPersons(Set<Person> persons) {
        this.persons = persons;
    }
}
