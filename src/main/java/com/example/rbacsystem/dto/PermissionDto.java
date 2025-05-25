package com.example.rbacsystem.dto;

public class PermissionDto {
    private String module;
    private String action;

    public PermissionDto() {}

    public PermissionDto(String module, String action) {
        this.module = module;
        this.action = action;
    }

    // Getters and Setters
    public String getModule() { return module; }
    public void setModule(String module) { this.module = module; }

    public String getAction() { return action; }
    public void setAction(String action) { this.action = action; }
}