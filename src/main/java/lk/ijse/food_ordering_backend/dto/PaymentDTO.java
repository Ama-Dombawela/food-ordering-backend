package lk.ijse.food_ordering_backend.dto;

import lk.ijse.food_ordering_backend.entity.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentDTO implements Serializable {
    private Long id;
    private Long orderId;
    private PaymentStatus status;
    private String paymentMethod;
    private LocalDateTime paidAt;
}