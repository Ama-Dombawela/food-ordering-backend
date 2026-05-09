package lk.ijse.food_ordering_backend.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import lk.ijse.food_ordering_backend.entity.Order;

// Handles database access for orders.
@Repository
public interface OrderDao extends JpaRepository<Order, Long> {

	List<Order> findByUser_Id(Long userId);
}
