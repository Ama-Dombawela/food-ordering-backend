package lk.ijse.food_ordering_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

// DTO for transferring authentication data between the API and the service layer.
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthDTO implements Serializable {
    private String email;
    private Long userId;
    private String password;
    private String token;
    private String role;
}