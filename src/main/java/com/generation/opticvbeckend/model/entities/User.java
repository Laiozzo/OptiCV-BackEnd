package com.generation.opticvbeckend.model.entities;

import jakarta.persistence.Entity;

@Entity
public class User extends BaseEntity
{
    private String name;
    private String surname;
    private String username;
    private String email;
    private String hashedPassword;


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

    public String getHashedPassword() {return hashedPassword;}

    public void setHashedPassword(String hashedPassword) {this.hashedPassword = hashedPassword;}

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }
}
