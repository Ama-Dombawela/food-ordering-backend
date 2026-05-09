package lk.ijse.food_ordering_backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lk.ijse.food_ordering_backend.dto.AuthDTO;
import lk.ijse.food_ordering_backend.service.AuthService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    // POST /api/auth/register
    @PostMapping("/register")
    public ResponseEntity<AuthDTO> register(@RequestBody AuthDTO authDTO) {
        return new ResponseEntity<>(authService.register(authDTO), HttpStatus.CREATED);
    }

    // POST /api/auth/login
    @PostMapping("/login")
    public ResponseEntity<AuthDTO> login(@RequestBody AuthDTO authDTO) {
        return new ResponseEntity<>(authService.login(authDTO), HttpStatus.OK);
    }
}
