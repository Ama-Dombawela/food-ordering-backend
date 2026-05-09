package lk.ijse.food_ordering_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthDTO implements Serializable {
    private String email;
    private String password;
    private String token;
    private String role;
}