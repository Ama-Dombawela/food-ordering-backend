package lk.ijse.food_ordering_backend.dto;

import java.io.Serializable;
 
import lk.ijse.food_ordering_backend.entity.FoodItemStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// DTO for transferring food item details between the API and the service layer.
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FoodItemDTO implements Serializable {

    private Long id;
    private String name;
    private String description;
    private Double price;
    private FoodItemStatus status;
    private Long categoryId;
}
