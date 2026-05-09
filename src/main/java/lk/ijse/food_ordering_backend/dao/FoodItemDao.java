package lk.ijse.food_ordering_backend.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import lk.ijse.food_ordering_backend.entity.FoodItem;
import lk.ijse.food_ordering_backend.entity.FoodItemStatus;

@Repository
public interface FoodItemDao extends JpaRepository<FoodItem, Long> {
    List<FoodItem> findByStatus(FoodItemStatus status);

    List<FoodItem> findByCategoryId(Long categoryId);
}
