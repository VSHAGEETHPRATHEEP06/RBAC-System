package com.example.rbacsystem.dto;

import com.example.rbacsystem.entity.User;
import java.util.List;
import java.util.stream.Collectors;

public class UserResponse {
    private Long id;
    private String username;
    private String email;
    private String name;
    private Boolean firstLogin;
    private RoleResponse role;

    public UserResponse() {}

    public UserResponse(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.name = user.getName();
        this.firstLogin = user.getFirstLogin();
        this.role = new RoleResponse(user.getRole());
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Boolean getFirstLogin() { return firstLogin; }
    public void setFirstLogin(Boolean firstLogin) { this.firstLogin = firstLogin; }

    public RoleResponse getRole() { return role; }
    public void setRole(RoleResponse role) { this.role = role; }

    public static class RoleResponse {
        private Long id;
        private String name;

        public RoleResponse() {}

        public RoleResponse(com.example.rbacsystem.entity.Role role) {
            this.id = role.getId();
            this.name = role.getName();
        }

        // Getters and Setters
        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
    }
}