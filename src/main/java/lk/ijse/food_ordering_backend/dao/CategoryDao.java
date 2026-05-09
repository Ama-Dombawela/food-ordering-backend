package lk.ijse.food_ordering_backend.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import lk.ijse.food_ordering_backend.entity.Category;

@Repository
public interface CategoryDao extends JpaRepository<Category, Long> {

	boolean existsByName(String name);

}
