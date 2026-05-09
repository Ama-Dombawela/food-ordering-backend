package lk.ijse.food_ordering_backend.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryDTO implements Serializable {

    private Long id;
    private String name;
}
