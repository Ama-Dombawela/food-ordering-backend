package lk.ijse.food_ordering_backend.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CartDTO implements Serializable {

    private Long id;
    private Long userId;
    private List<CartItemDTO> cartItems;
    private BigDecimal totalPrice;

}
