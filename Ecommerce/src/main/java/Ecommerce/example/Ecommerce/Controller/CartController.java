package Ecommerce.example.Ecommerce.Controller;

import Ecommerce.example.Ecommerce.Entity.Cart;
import Ecommerce.example.Ecommerce.Entity.products;
import Ecommerce.example.Ecommerce.ServiceImpl.CartServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/v1/carts")
public class CartController {

    private CartServiceImpl cartServiceImpl;

    public CartController(CartServiceImpl cartServiceImpl) {
        this.cartServiceImpl = cartServiceImpl;
    }

    @Operation(summary = "Create a new cart")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cart created successfully",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Cart.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content)
    })
    @PostMapping
    public ResponseEntity<Cart> createCart(@RequestBody Cart cart) {
        Cart createdCart = cartServiceImpl.createCart(cart);
        return ResponseEntity.status(CREATED).body(createdCart);
    }

    @Operation(summary = "Get all carts")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of carts",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Cart.class))})
    })
    @GetMapping
    public ResponseEntity<List<Cart>> getAllCarts() {
        List<Cart> carts = cartServiceImpl.getAllCarts();
        return ResponseEntity.ok(carts);
    }

    @Operation(summary = "Get a cart by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cart found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Cart.class))}),
            @ApiResponse(responseCode = "404", description = "Cart not found",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<Cart> getCartById(@PathVariable Long id) {
        Cart cart = cartServiceImpl.getCartById(id);
        return ResponseEntity.ok(cart);
    }

    @Operation(summary = "Delete a cart by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cart deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Cart not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCartById(@PathVariable Long id) {
        cartServiceImpl.deleteCartById(id);
        return ResponseEntity.ok().build();
    }
    @Operation(summary = "Add a product to a cart")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product added to cart",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Cart.class))}),
            @ApiResponse(responseCode = "404", description = "Cart or product not found",
                    content = @Content)
    })
    @PostMapping("/{cartId}/products")
    public ResponseEntity<Cart> addProductToCart(@PathVariable Long cartId, @RequestBody products product) {
        Cart updatedCart = cartServiceImpl.addProductToCart(cartId, product);
        return ResponseEntity.ok(updatedCart);
    }
}
