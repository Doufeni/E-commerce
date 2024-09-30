package Ecommerce.example.Ecommerce.ServiceImpl;


import Ecommerce.example.Ecommerce.Entity.Cart;
import Ecommerce.example.Ecommerce.Entity.products;
import Ecommerce.example.Ecommerce.Repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl {

    @Autowired
    public CartRepository cartRepository;

    public Cart createCart(Cart cart) {
        return cartRepository.save(cart);
    }

   public Cart getCartById(Long id) {
       return cartRepository.findById(id)
               .orElseThrow(() -> new RuntimeException("Cart not found"));
    }

    public Cart addProductToCart(Long cartId, products product) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new RuntimeException("Cart not found"));
        cart.getProducts().add(product);
        return cartRepository.save(cart);
    }

    public void deleteCartById(Long id) {
        Cart cart = cartRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cart not found"));
        cartRepository.delete(cart);
    }
}

