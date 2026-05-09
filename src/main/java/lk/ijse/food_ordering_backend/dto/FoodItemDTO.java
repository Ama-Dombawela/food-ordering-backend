package lk.ijse.food_ordering_backend.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import lk.ijse.food_ordering_backend.entity.FoodItemStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FoodItemDTO implements Serializable {

    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private FoodItemStatus status;
    private Long categoryId;
    private String categoryName;
}
