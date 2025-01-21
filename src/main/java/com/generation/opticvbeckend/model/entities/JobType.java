package com.generation.opticvbeckend.model.entities;

import jakarta.persistence.Entity;

@Entity
public class JobType extends BaseEntity
{
    //Proprietà
    private String jobName;
    private String description;


    //Getter and setter

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
