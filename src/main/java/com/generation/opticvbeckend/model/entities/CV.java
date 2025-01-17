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
    private String gender;
    private String contactDetails;
    private String education;
    private String workExperience;
    private String skills;

    public CV(String name, String surname, String city, String country, String gender, String contactDetails, String education, String workExperience, String skills, String patent) {
        this.name = name;
        this.gender = gender;
        this.contactDetails = contactDetails;
        this.education = education;
        this.workExperience = workExperience;
        this.skills = skills;
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

    @Override
    public String toString() {
        return "CV {" +
                "\n Name: " + name +
                "\n Contact Details: " + contactDetails +
                "\n Education: " + education +
                "\n Work Experience: " + workExperience +
                "\n Skills: " + skills +
                "\n}";
    }

}
