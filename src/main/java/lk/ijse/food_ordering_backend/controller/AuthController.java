package lk.ijse.food_ordering_backend.controller;

import lk.ijse.food_ordering_backend.dto.AuthDTO;
import lk.ijse.food_ordering_backend.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// Handles login and registration requests.
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    // POST /api/auth/register
    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthDTO> register(@RequestBody AuthDTO authDTO) {
        return new ResponseEntity<>(authService.register(authDTO), HttpStatus.CREATED);
    }

    // POST /api/auth/login
    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthDTO> login(@RequestBody AuthDTO authDTO) {
        return new ResponseEntity<>(authService.login(authDTO), HttpStatus.OK);
    }
}