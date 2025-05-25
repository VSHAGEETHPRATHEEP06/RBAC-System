package com.example.rbacsystem.dto;

import jakarta.validation.constraints.Email;

public class UserUpdateRequest {
    private String name;
    
    @Email
    private String email;

    public UserUpdateRequest() {}

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}