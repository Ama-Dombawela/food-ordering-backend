package lk.ijse.food_ordering_backend.dto;

import java.io.Serializable;
 
import java.time.LocalDateTime;
import java.util.List;
import lk.ijse.food_ordering_backend.entity.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// DTO for transferring order details between the API and the service layer.
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDTO implements Serializable {

    private Long id;
    private Long userId;
    private OrderStatus status;
    private Double totalAmount;
    private LocalDateTime orderDate;
    private List<OrderItemDTO> orderItems;
}
