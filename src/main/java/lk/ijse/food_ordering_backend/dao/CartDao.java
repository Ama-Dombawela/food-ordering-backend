package lk.ijse.food_ordering_backend.dao;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import lk.ijse.food_ordering_backend.entity.Cart;

@Repository
public interface CartDao extends JpaRepository<Cart, Long> {

    Optional<Cart> findByUserId(Long userId);
}
