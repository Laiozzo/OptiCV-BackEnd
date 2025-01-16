package com.generation.opticvbeckend.model.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class User extends BaseEntity
{
    private String name;
    private String surname;
    private String email;
    private String password;


    //relationship
    @OneToMany(mappedBy = "User", cascade = CascadeType.ALL)
    private List<CV> curriculum;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
