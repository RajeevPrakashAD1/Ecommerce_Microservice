package Ecommerce.Product_Service.repository;

import Ecommerce.Product_Service.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    // Additional query methods can be defined here if needed
}
