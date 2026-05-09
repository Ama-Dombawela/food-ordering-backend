package lk.ijse.food_ordering_backend.dto;

import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// DTO for transferring cart details between the API and the service layer.
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CartDTO implements Serializable {

    private Long id;
    private Long userId;
    private List<CartItemDTO> cartItems;

}
