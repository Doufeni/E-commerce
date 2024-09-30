package Ecommerce.example.Ecommerce.Controller;

import Ecommerce.example.Ecommerce.Entity.Cart;
import Ecommerce.example.Ecommerce.Entity.Customer;
import Ecommerce.example.Ecommerce.ServiceImpl.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {


    public CustomerServiceImpl customerServiceimpl;

    public CustomerController(CustomerServiceImpl customerServiceimpl){
        this.customerServiceimpl = customerServiceimpl;
    }

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        Customer createdCustomer = customerServiceimpl.createCustomer(customer);
        return ResponseEntity.ok(createdCustomer);
    }

    @PostMapping("/{customerId}/cart/addProduct/{productId}")
    public ResponseEntity<Cart> addProductToCart(
            @PathVariable Long customerId, @PathVariable Long productId) {
        Cart updatedCart = customerServiceimpl.addProductToCustomerCart(customerId, productId);
        return ResponseEntity.ok(updatedCart);
    }

    @DeleteMapping("/{customerId}/cart/removeProduct/{productId}")
    public ResponseEntity<Cart> removeProductFromCart(
            @PathVariable Long customerId, @PathVariable Long productId) {
        Cart updatedCart = customerServiceimpl.removeProductFromCustomerCart(customerId, productId);
        return ResponseEntity.ok(updatedCart);
    }
}
