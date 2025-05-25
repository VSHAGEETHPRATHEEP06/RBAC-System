package com.example.rbacsystem.service;

import org.springframework.stereotype.Service;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class TokenBlacklistService {
    
    // In production, use Redis or database for distributed systems
    private final Set<String> blacklistedTokens = ConcurrentHashMap.newKeySet();
    
    public void blacklistToken(String token) {
        blacklistedTokens.add(token);
    }
    
    public boolean isTokenBlacklisted(String token) {
        return blacklistedTokens.contains(token);
    }
    
    // Optional: Method to clean up expired tokens periodically
    public void removeExpiredTokens() {
        // Implementation would check token expiration and remove expired ones
        // This would typically be called by a scheduled task
    }
}