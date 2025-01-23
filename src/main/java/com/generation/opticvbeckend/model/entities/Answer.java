package com.generation.opticvbeckend.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Lob;

@Entity
public class Answer extends BaseEntity {
    @Lob
    String response;
    @Lob
    String question;

    public Answer() {}
    public Answer(String question, String response) {
        this.question = question;
        this.response = response;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
