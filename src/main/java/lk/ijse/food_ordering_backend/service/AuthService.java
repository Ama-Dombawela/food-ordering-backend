package lk.ijse.food_ordering_backend.service;

import lk.ijse.food_ordering_backend.dto.AuthDTO;

public interface AuthService {

    AuthDTO register(AuthDTO authDTO);
    AuthDTO login(AuthDTO authDTO);
}
