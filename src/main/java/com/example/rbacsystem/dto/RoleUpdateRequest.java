package com.example.rbacsystem.dto;

import jakarta.validation.constraints.NotNull;

public class RoleUpdateRequest {
    @NotNull
    private Long roleId;

    public RoleUpdateRequest() {}

    // Getters and Setters
    public Long getRoleId() { return roleId; }
    public void setRoleId(Long roleId) { this.roleId = roleId; }
}