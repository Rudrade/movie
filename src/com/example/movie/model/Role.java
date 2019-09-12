package com.example.movie.model;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.util.List;

@XmlType(propOrder = {
        "id",
        "name"
})
@XmlRootElement
@Entity
@Table(name = "Role")
@XmlAccessorType(XmlAccessType.NONE)
public class Role {
    @XmlElement
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private int id;

    @XmlElement
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @OneToMany
    List<User> userList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
}
