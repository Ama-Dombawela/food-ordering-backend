package lk.ijse.food_ordering_backend.service.impl;

import lk.ijse.food_ordering_backend.dao.UserDao;
import lk.ijse.food_ordering_backend.dto.AuthDTO;
import lk.ijse.food_ordering_backend.entity.Role;
import lk.ijse.food_ordering_backend.entity.User;
import lk.ijse.food_ordering_backend.exception.DataNotFoundException;
import lk.ijse.food_ordering_backend.exception.DuplicateEntryException;
import lk.ijse.food_ordering_backend.security.JWTUtil;
import lk.ijse.food_ordering_backend.service.AuthService;
import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// Handles login and registration logic.
@Service
@Transactional
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;
    private final JWTUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    // Register a new customer and return a JWT token.
    @Override
    public AuthDTO register(AuthDTO authDTO) {
        // Check if email already exists
        if (userDao.existsByEmail(authDTO.getEmail())) {
            throw new DuplicateEntryException("Email already registered: " + authDTO.getEmail());
        }

        // Build and save new user
        User user = new User();
        String name = authDTO.getEmail();
        if (name != null && name.contains("@")) {
            name = name.substring(0, name.indexOf('@'));
        }
        user.setName(name);
        user.setEmail(authDTO.getEmail());
        user.setPassword(passwordEncoder.encode(authDTO.getPassword()));
        user.setRole(Role.CUSTOMER); // default role on register

        userDao.save(user);

        // Generate token
        String token = jwtUtil.generateToken(user.getEmail(), user.getRole().name(), user.getId());

        // Build and return response
        AuthDTO response = new AuthDTO();
        response.setEmail(user.getEmail());
        response.setToken(token);
        response.setRole(user.getRole().name());
        response.setUserId(user.getId());
        return response;
    }

    // Authenticate a user and return a JWT token.
    @Override
    public AuthDTO login(AuthDTO authDTO) {
        // Authenticate - throws BadCredentialsException if wrong
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authDTO.getEmail(),
                        authDTO.getPassword()));

        // Load user from DB
        User user = userDao.findByEmail(authDTO.getEmail())
                .orElseThrow(() -> new DataNotFoundException("User not found"));

        // Generate token
        String token = jwtUtil.generateToken(user.getEmail(), user.getRole().name(), user.getId());

        // Build and return response
        AuthDTO response = new AuthDTO();
        response.setEmail(user.getEmail());
        response.setToken(token);
        response.setRole(user.getRole().name());
        response.setUserId(user.getId());
        return response;
    }
}
