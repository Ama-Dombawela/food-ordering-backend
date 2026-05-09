package lk.ijse.food_ordering_backend.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import lk.ijse.food_ordering_backend.entity.OrderItem;

// Handles database access for order items.
@Repository
public interface OrderItemDao extends JpaRepository<OrderItem, Long> {

}
