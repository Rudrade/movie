package com.example.movie.model;

import javax.persistence.*;
import javax.xml.bind.annotation.*;

@Entity
@Table(name = "User")
@XmlRootElement
@XmlType(propOrder = {
        "id",
        "username",
        "password",
        "role"
})
@XmlAccessorType(XmlAccessType.FIELD)
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

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    @XmlElement
    private Role role;

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

    @XmlTransient
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
