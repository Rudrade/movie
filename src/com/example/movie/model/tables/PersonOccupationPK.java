package com.example.movie.model.tables;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class PersonOccupationPK implements Serializable {
    @Column(name = "person_id")
    private int personId;

    @Column(name = "occupation_id")
    private int occupationId;
}
