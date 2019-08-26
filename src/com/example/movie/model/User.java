package com.example.movie.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@Entity
@Table(name = "User")
@XmlRootElement
@XmlType(propOrder = {
        "id",
        "username",
        "password"
})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    @XmlElement
    private int id;

    @Column(name = "username", unique = true, nullable = false)
    @XmlElement
    private String username;

    @Column(name = "password", unique = true, nullable = false)
    @XmlElement
    private String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
