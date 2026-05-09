package lk.ijse.food_ordering_backend.dto;

import java.io.Serializable;
import lk.ijse.food_ordering_backend.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// DTO for transferring user details between the API and the service layer.
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO implements Serializable {

    private Long id;
    private String name;
    private String email;
    private Role role;
}
