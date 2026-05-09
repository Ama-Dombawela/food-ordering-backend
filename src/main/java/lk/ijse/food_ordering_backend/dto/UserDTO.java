package lk.ijse.food_ordering_backend.dto;

import java.io.Serializable;
import lk.ijse.food_ordering_backend.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO implements Serializable {

    private Long id;
    private String username;
    private String email;
    private String password;
    private Role role;
}
