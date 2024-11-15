package com.contentwise.challenge.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.Serializable;

@Entity
@Table(name = "`user`")
public class User implements Serializable {


    @Id
    Long id;

    String username;

    public User() {
    }

    public User(Long id, String username) {
        this.id = id;
        this.username = username;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }
}
