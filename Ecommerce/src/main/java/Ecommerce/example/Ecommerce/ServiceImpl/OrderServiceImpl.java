package Ecommerce.example.Ecommerce.ServiceImpl;

import Ecommerce.example.Ecommerce.Entity.Cart;
import Ecommerce.example.Ecommerce.Entity.Customer;
import Ecommerce.example.Ecommerce.Entity.CustomerOrder;
import Ecommerce.example.Ecommerce.Enum.OrderStatus;
import Ecommerce.example.Ecommerce.Repository.CustomerRepository;
import Ecommerce.example.Ecommerce.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl {

    @Autowired
    public OrderRepository orderRepository;
    @Autowired
    public CustomerRepository customerRepository;

    public List<CustomerOrder> findAllOrders() {
        return orderRepository.findAll();
    }


    public CustomerOrder createOrder(Long customerId) {
        // Find the customer by ID
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        // Create a new order and assign the cart products to the order
        CustomerOrder customerOrder = new CustomerOrder();
        customerOrder.setCustomer(customer);
        customerOrder.setStatus(OrderStatus.IN_PROGRESS);  // Set the initial order status

        return orderRepository.save(customerOrder);
    }
}
