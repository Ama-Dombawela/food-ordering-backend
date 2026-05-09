package lk.ijse.food_ordering_backend.service;

import lk.ijse.food_ordering_backend.dto.UserDTO;

import java.util.List;

public interface UserService {

    // Retrieve all users.
    List<UserDTO> getAllUsers();

    // Retrieve a user by id.
    UserDTO getUser(Long id);

    // Update a user by id.
    void updateUser(Long id, UserDTO userDTO);

    // Delete a user by id.
    void deleteUser(Long id);
}