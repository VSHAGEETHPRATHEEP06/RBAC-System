package com.example.rbacsystem.dto;

import java.util.List;

public class LoginResponse {
    private String token;
    private String username;
    private String role;
    private List<PermissionDto> permissions;

    public LoginResponse() {}

    public LoginResponse(String token, String username, String role, List<PermissionDto> permissions) {
        this.token = token;
        this.username = username;
        this.role = role;
        this.permissions = permissions;
    }

    // Getters and Setters
    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public List<PermissionDto> getPermissions() { return permissions; }
    public void setPermissions(List<PermissionDto> permissions) { this.permissions = permissions; }
}