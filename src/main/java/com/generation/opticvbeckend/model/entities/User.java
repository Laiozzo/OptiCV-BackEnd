package com.generation.opticvbeckend.model.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class User extends BaseEntity
{
    private String name;
    private String surname;
    private String email;
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UploadedFile> uploadedFiles = new ArrayList<>();


    //relationship
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<CV> curriculum = new ArrayList<>();

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

    public List<UploadedFile> getUploadedFiles() {
        return uploadedFiles;
    }

    public void setUploadedFiles(List<UploadedFile> uploadedFiles) {
        this.uploadedFiles = uploadedFiles;
    }

    public List<CV> getCurriculum() {
        return curriculum;
    }

    public void setCurriculum(List<CV> curriculum) {
        this.curriculum = curriculum;
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
