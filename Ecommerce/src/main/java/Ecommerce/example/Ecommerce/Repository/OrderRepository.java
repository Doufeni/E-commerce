package Ecommerce.example.Ecommerce.Repository;

import Ecommerce.example.Ecommerce.Entity.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<CustomerOrder, Long> {
}
