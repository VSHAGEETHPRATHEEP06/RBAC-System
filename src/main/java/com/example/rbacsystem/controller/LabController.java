package com.example.rbacsystem.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/lab")
@CrossOrigin(origins = "*")
public class LabController {

    @GetMapping("/analytics")
    @PreAuthorize("hasAuthority('LAB_ANALYTICS:VIEW')")
    public ResponseEntity<Map<String, Object>> getAnalytics() {
        Map<String, Object> analytics = new HashMap<>();
        analytics.put("totalTests", 1250);
        analytics.put("pendingTests", 45);
        analytics.put("completedToday", 78);
        analytics.put("message", "Lab analytics data retrieved successfully");
        
        return ResponseEntity.ok(analytics);
    }

    @PostMapping("/test-request")
    @PreAuthorize("hasAuthority('TEST_REQUEST:CREATE')")
    public ResponseEntity<Map<String, Object>> createTestRequest(@RequestBody Map<String, Object> request) {
        Map<String, Object> response = new HashMap<>();
        response.put("id", 12345);
        response.put("status", "PENDING");
        response.put("message", "Test request created successfully");
        response.put("requestData", request);
        
        return ResponseEntity.ok(response);
    }

    @GetMapping("/test-request")
    @PreAuthorize("hasAuthority('TEST_REQUEST:VIEW')")
    public ResponseEntity<Map<String, Object>> getTestRequests() {
        Map<String, Object> response = new HashMap<>();
        response.put("requests", new Object[]{
            Map.of("id", 1, "patientName", "John Doe", "testType", "Blood Test", "status", "PENDING"),
            Map.of("id", 2, "patientName", "Jane Smith", "testType", "X-Ray", "status", "COMPLETED")
        });
        response.put("total", 2);
        response.put("message", "Test requests retrieved successfully");
        
        return ResponseEntity.ok(response);
    }
}