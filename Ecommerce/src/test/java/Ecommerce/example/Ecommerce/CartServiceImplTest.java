package Ecommerce.example.Ecommerce;

import Ecommerce.example.Ecommerce.Entity.Cart;
import Ecommerce.example.Ecommerce.Entity.products;
import Ecommerce.example.Ecommerce.Repository.CartRepository;
import Ecommerce.example.Ecommerce.ServiceImpl.CartServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CartServiceImplTest {

    @Mock
    private CartRepository cartRepository;

    @InjectMocks
    private CartServiceImpl cartService;

    private Cart cart;
    private products product;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);  // Initialize mocks
        cart = new Cart();                   // Create a new Cart object
        product = new products();            // Create a new Product object
        product.setId(1L);
        product.setTitle("Test Product");
        product.setAvailable_quantity(10);
    }

    @Test
    void testCreateCart() {
        when(cartRepository.save(cart)).thenReturn(cart);

        Cart createdCart = cartService.createCart(cart);

        assertNotNull(createdCart);
        verify(cartRepository, times(1)).save(cart);
    }

    @Test
    void testGetCartById() {
        when(cartRepository.findById(1L)).thenReturn(Optional.of(cart));

        Cart fetchedCart = cartService.getCartById(1L);

        assertNotNull(fetchedCart);
        verify(cartRepository, times(1)).findById(1L);
    }

    @Test
    void testGetCartById_CartNotFound() {
        when(cartRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            cartService.getCartById(1L);
        });

        assertEquals("Cart not found", exception.getMessage());
        verify(cartRepository, times(1)).findById(1L);
    }

    @Test
    void testAddProductToCart() {
        when(cartRepository.findById(1L)).thenReturn(Optional.of(cart));
        when(cartRepository.save(cart)).thenReturn(cart);

        Cart updatedCart = cartService.addProductToCart(1L, product);

        assertTrue(updatedCart.getProducts().contains(product));
        verify(cartRepository, times(1)).findById(1L);
        verify(cartRepository, times(1)).save(cart);
    }

    @Test
    void testAddProductToCart_CartNotFound() {
        when(cartRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            cartService.addProductToCart(1L, product);
        });

        assertEquals("Cart not found", exception.getMessage());
        verify(cartRepository, times(1)).findById(1L);
    }

    @Test
    void testDeleteCartById() {
        when(cartRepository.findById(1L)).thenReturn(Optional.of(cart));
        doNothing().when(cartRepository).delete(cart);

        cartService.deleteCartById(1L);

        verify(cartRepository, times(1)).findById(1L);
        verify(cartRepository, times(1)).delete(cart);
    }

    @Test
    void testDeleteCartById_CartNotFound() {
        when(cartRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            cartService.deleteCartById(1L);
        });

        assertEquals("Cart not found", exception.getMessage());
        verify(cartRepository, times(1)).findById(1L);
    }
}
