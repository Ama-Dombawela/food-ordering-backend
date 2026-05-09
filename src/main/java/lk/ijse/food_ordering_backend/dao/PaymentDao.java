package lk.ijse.food_ordering_backend.dao;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import lk.ijse.food_ordering_backend.entity.Payment;

@Repository
public interface PaymentDao extends JpaRepository<Payment, Long> {

    Optional<Payment> findByOrderId(Long orderId);
}
