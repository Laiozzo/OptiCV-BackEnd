package com.generation.opticvbeckend.model.entities;

import jakarta.persistence.Entity;

@Entity
public class Answer extends BaseEntity {
    String response;
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
