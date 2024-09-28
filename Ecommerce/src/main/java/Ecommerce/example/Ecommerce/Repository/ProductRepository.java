package Ecommerce.example.Ecommerce.Repository;

import Ecommerce.example.Ecommerce.Entity.products;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<products, Long> {
}
