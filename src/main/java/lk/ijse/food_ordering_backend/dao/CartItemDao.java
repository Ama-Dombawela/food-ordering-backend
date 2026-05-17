package lk.ijse.food_ordering_backend.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import lk.ijse.food_ordering_backend.entity.CartItem;

// Handles database access for cart items.
@Repository
public interface CartItemDao extends JpaRepository<CartItem, Long> {
    List<CartItem> findByCart_Id(Long cartId);

    void deleteByCart_Id(Long cartId);

    void deleteByFoodItem_Id(Long foodItemId);
}
