package Ecommerce.example.Ecommerce.ServiceImpl;


import Ecommerce.example.Ecommerce.Entity.Cart;
import Ecommerce.example.Ecommerce.Entity.products;
import Ecommerce.example.Ecommerce.Repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl {

    private CartRepository cartRepository;

    public CartServiceImpl(CartRepository cartRepository){
        this.cartRepository = cartRepository;
    }

    public Cart createCart(Cart cart) {
        return cartRepository.save(cart);
    }

    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }
   public Cart getCartById(Long id) {
       return cartRepository.findById(id)
               .orElseThrow(() -> new RuntimeException("Cart not found"));
    }

    public Cart addProductToCart(Long cartId, products products) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new RuntimeException("Cart not found"));
        cart.getProducts().add(products);
        return cartRepository.save(cart);
    }

    // Other methods...
}

