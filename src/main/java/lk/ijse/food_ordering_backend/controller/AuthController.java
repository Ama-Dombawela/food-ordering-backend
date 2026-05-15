package lk.ijse.food_ordering_backend.controller;

import lk.ijse.food_ordering_backend.dto.AuthDTO;
import lk.ijse.food_ordering_backend.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

// Handles login and registration requests.
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    // POST /api/auth/register
    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> register(@RequestBody AuthDTO authDTO) {
        AuthDTO saved = authService.register(authDTO);
        Map<String, Object> body = new HashMap<>();
        body.put("message", "User registered successfully.");
        body.put("data", saved);
        return new ResponseEntity<>(body, HttpStatus.CREATED);
    }

    // POST /api/auth/login
    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> login(@RequestBody AuthDTO authDTO) {
        AuthDTO token = authService.login(authDTO);
        Map<String, Object> body = new HashMap<>();
        body.put("message", "Login successful.");
        body.put("data", token);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }
}