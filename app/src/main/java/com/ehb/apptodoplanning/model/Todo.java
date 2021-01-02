package com.ehb.apptodoplanning.model;

import java.io.Serializable;

public class Todo implements Serializable {
    private String title, description;

    public Todo(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Todo() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
