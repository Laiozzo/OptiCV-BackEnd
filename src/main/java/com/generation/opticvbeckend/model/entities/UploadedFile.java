package com.generation.opticvbeckend.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;

@Entity
public class UploadedFile extends BaseEntity {
    private String fileName;
    private String filePath;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @Lob // Per gestire grandi quantità di testo
    private String parsedContent;


    //Getter and Setter:


    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public String getParsedContent()
    {
        return parsedContent;
    }

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

    public void setParsedContent(String parsedText)
    {
        this.parsedContent = parsedText;
    }
}
