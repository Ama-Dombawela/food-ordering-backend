package lk.ijse.food_ordering_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
 
// DTO for transferring order item details between the API and the service layer.
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderItemDTO implements Serializable {
    private Long id;
    private Long orderId;
    private Long foodItemId;
    private Integer quantity;
    private Double price;
}