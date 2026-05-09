package lk.ijse.food_ordering_backend.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CartItemDTO implements Serializable {

    private Long id;
    private Long cartId;
    private Long foodItemId;
    private String foodItemName;
    private Integer quantity;
    private BigDecimal price;
}
