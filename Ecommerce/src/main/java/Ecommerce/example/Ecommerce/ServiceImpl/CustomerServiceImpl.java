package Ecommerce.example.Ecommerce.ServiceImpl;

import Ecommerce.example.Ecommerce.Entity.Cart;
import Ecommerce.example.Ecommerce.Entity.Customer;
import Ecommerce.example.Ecommerce.Entity.products;
import Ecommerce.example.Ecommerce.Repository.CartRepository;
import Ecommerce.example.Ecommerce.Repository.CustomerRepository;
import Ecommerce.example.Ecommerce.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl {

    public CustomerRepository customerRepository;
    public ProductRepository productRepository;
    public CartRepository cartRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository, CartRepository cartRepository, ProductRepository productRepository) {
        this.customerRepository = customerRepository;
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    public Customer createCustomer(Customer customer) {
        Cart cart = new Cart();
        Cart savedCart = cartRepository.save(cart);
        customer.setCart(savedCart);
        return customerRepository.save(customer);
    }

    public Cart addProductToCustomerCart(Long customerId, Long productId) {
        try {
            Customer customer = customerRepository.findById(customerId)
                    .orElseThrow(() -> new RuntimeException("Customer not found"));
            Cart cart = customer.getCart();
            if (cart == null) {
                throw new RuntimeException("Cart not found for customer");
            }
            products product = productRepository.findById(productId)
                    .orElseThrow(() -> new RuntimeException("Product not found"));
            cart.setCustomerName(customer.getFirstName());
            cart.getProducts().add(product);
            return cartRepository.save(cart);
        }  catch (Exception e) {
            throw new RuntimeException("Error adding product to cart: " + e.getMessage());
        }

    }

    public Cart removeProductFromCustomerCart(Long customerId, Long productId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        Cart cart = customer.getCart();
        if (cart == null) {
            throw new RuntimeException("Cart not found for customer");
        }
        products product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        if (!cart.getProducts().remove(product)) {
            throw new RuntimeException("Product not found in cart");
        }
        return cartRepository.save(cart);
    }
    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }



}
