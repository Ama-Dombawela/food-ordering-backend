package lk.ijse.food_ordering_backend.dao;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import lk.ijse.food_ordering_backend.entity.Payment;

// Handles database access for payments.
@Repository
public interface PaymentDao extends JpaRepository<Payment, Long> {

    Optional<Payment> findByOrder_Id(Long orderId);
}
