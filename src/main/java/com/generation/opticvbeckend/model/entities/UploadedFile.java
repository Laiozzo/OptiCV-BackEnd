package com.generation.opticvbeckend.model.entities;

import jakarta.persistence.Entity;

@Entity
public class UploadedFile extends BaseEntity {
    private String fileName;
    private String filePath;


    //Getter and Setter:

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
