package com.example.rbacsystem.service;

import com.example.rbacsystem.dto.LoginRequest;
import com.example.rbacsystem.dto.LoginResponse;
import com.example.rbacsystem.dto.PermissionDto;
import com.example.rbacsystem.entity.User;
import com.example.rbacsystem.security.CustomUserDetailsService;
import com.example.rbacsystem.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private TokenBlacklistService tokenBlacklistService;

    public LoginResponse login(LoginRequest loginRequest) {
        try {
            // Authenticate user
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    loginRequest.getUsername(), 
                    loginRequest.getPassword()
                )
            );
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Invalid username or password", e);
        }

        // Load user details
        final UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getUsername());
        final User user = userDetailsService.getUserByUsername(loginRequest.getUsername());

        // Prepare extra claims for JWT
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("role", user.getRole().getName());
        extraClaims.put("userId", user.getId());

        // Generate JWT token
        final String token = jwtUtil.generateTokenWithClaims(userDetails, extraClaims);

        // Prepare permissions list
        List<PermissionDto> permissions = user.getRole().getPermissions().stream()
                .map(permission -> new PermissionDto(permission.getModule(), permission.getAction()))
                .collect(Collectors.toList());

        return new LoginResponse(token, user.getUsername(), user.getRole().getName(), permissions);
    }

    public void logout(String token) {
        // Remove "Bearer " prefix if present
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        
        // Add token to blacklist
        tokenBlacklistService.blacklistToken(token);
    }
}