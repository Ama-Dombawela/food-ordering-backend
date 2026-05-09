package lk.ijse.food_ordering_backend.dto;

import java.io.Serializable;
 
import java.time.LocalDateTime;
import lk.ijse.food_ordering_backend.entity.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// DTO for transferring payment details between the API and the service layer.
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentDTO implements Serializable {
    private Long id;
    private Long orderId;
    private Double amount;
    private PaymentStatus status;
    private LocalDateTime paymentDate;
}