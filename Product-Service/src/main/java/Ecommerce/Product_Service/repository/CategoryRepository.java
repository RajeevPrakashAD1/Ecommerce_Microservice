package Ecommerce.Product_Service.repository;

import Ecommerce.Product_Service.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {
    // Additional query methods can be defined here if needed

}
