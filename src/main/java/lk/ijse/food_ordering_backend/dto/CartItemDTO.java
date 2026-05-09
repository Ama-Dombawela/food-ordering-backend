package lk.ijse.food_ordering_backend.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// DTO for transferring cart item details between the API and the service layer.
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CartItemDTO implements Serializable {

    private Long id;
    private Long cartId;
    private Long foodItemId;
    private Integer quantity;
}
