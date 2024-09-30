package Ecommerce.example.Ecommerce.Repository;

import Ecommerce.example.Ecommerce.Entity.Cart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends CrudRepository<Cart, Long> {
}

