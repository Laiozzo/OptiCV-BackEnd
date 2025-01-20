package com.generation.opticvbeckend.model.DTO;

public class CVDTO {
    private String name;
    private String gender;
    private String contactDetails;
    private String education;
    private String workExperience;
    private String skills;

    //Costruttore
    public CVDTO(String name, String gender, String contactDetails, String education, String workExperience, String skills) {
        this.name = name;
        this.gender = gender;
        this.contactDetails = contactDetails;
        this.education = education;
        this.workExperience = workExperience;
        this.skills = skills;
    }

    //Getter and setter
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
        return "CVDTO{" +
                "name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", contactDetails='" + contactDetails + '\'' +
                ", education='" + education + '\'' +
                ", workExperience='" + workExperience + '\'' +
                ", skills='" + skills + '\'' +
                '}';
    }
}
