package lk.ijse.food_ordering_backend.controller;

import lk.ijse.food_ordering_backend.dto.UserDTO;
import lk.ijse.food_ordering_backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Handles user API requests.
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // Retrieve all users.
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> getAllUsers() {
        List<UserDTO> list = userService.getAllUsers();
        Map<String, Object> body = new HashMap<>();
        body.put("message", "Users retrieved successfully.");
        body.put("data", list);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    // Retrieve a user by id.
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> getUser(@PathVariable Long id) {
        UserDTO dto = userService.getUser(id);
        Map<String, Object> body = new HashMap<>();
        body.put("message", "User retrieved successfully.");
        body.put("data", dto);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    // Update a user.
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        userService.updateUser(id, userDTO);
        Map<String, Object> body = new HashMap<>();
        body.put("message", "User updated successfully.");
        body.put("data", null);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    // Delete a user.
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        Map<String, Object> body = new HashMap<>();
        body.put("message", "User deleted successfully.");
        body.put("data", null);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }
}