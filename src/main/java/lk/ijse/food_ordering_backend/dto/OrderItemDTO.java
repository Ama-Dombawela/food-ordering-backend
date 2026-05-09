package lk.ijse.food_ordering_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderItemDTO implements Serializable {
    private Long id;
    private Long orderId;
    private Long foodItemId;
    private String foodItemName;
    private Integer quantity;
    private BigDecimal price;
}