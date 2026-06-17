package com.processmind.common.controller;

import com.processmind.common.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/health")
@Tag(name = "Health Check", description = "API health monitoring endpoints")
public class HealthController {

    @GetMapping
    @Operation(summary = "Check API health", description = "Returns the health status of the API")
    public ResponseEntity<ApiResponse<Map<String, Object>>> health() {
        Map<String, Object> data = new HashMap<>();
        data.put("status", "UP");
        data.put("service", "processmind-api");
        data.put("timestamp", System.currentTimeMillis());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.success(data, "Application is healthy"));
    }
}