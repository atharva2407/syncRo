package com.syncro.syncro.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "tasks")
public class Task {

    @Id
    private String id;
    private String title;
    private String description;
    private boolean completed = false;
    private String userId; // Link task to a specific user
    private LocalDate dueDate;


    // Getter and Setter for id
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    // Getter and Setter for title
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    // Getter and Setter for description
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    // Getter and Setter for completed
    public boolean isCompleted() {
        return completed;
    }
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    // Getter and Setter for userId
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }
}
