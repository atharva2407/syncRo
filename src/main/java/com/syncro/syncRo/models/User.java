package com.syncro.syncRo.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class User {
    private String id;
    private String username;
    private String password; // store encoded password!
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    // You can add roles/permissions fields if needed
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    // getters and setters
}
