package lk.ijse.food_ordering_backend.service;

import lk.ijse.food_ordering_backend.dto.AuthDTO;

// Defines login and registration operations.
public interface AuthService {

    // Register a new user.
    AuthDTO register(AuthDTO authDTO);

    // Authenticate a user.
    AuthDTO login(AuthDTO authDTO);
}
