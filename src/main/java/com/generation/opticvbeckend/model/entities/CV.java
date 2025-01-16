package com.generation.opticvbeckend.model.entities;

import jakarta.persistence.*;

@Entity
public class CV extends BaseEntity
{
    //Relationship
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    private String name;
    private String surname;
    private String city;
    private String country;
    private String gender;
    private String contactDetails;
    private String education;
    private String workExperience;
    private String skills;
    private String patent;

    public CV(String name, String surname, String city, String country, String gender, String contactDetails, String education, String workExperience, String skills, String patent) {
        this.name = name;
        this.surname = surname;
        this.city = city;
        this.country = country;
        this.gender = gender;
        this.contactDetails = contactDetails;
        this.education = education;
        this.workExperience = workExperience;
        this.skills = skills;
        this.patent = patent;
    }

    public CV() {

    }


    //Getter and Setter

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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getContactDetails() {
        return contactDetails;
    }

    public void setContactDetails(String contactDetails) {
        this.contactDetails = contactDetails;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getWorkExperience() {
        return workExperience;
    }

    public void setWorkExperience(String workExperience) {
        this.workExperience = workExperience;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }
}
