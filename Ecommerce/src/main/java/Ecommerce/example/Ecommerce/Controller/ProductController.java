package Ecommerce.example.Ecommerce.Controller;


import Ecommerce.example.Ecommerce.Entity.products;
import Ecommerce.example.Ecommerce.ServiceImpl.ProductServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/products")
public class ProductController {

    private ProductServiceImpl productServiceImpl;
    public ProductController(ProductServiceImpl productServiceImpl) {

        this.productServiceImpl = productServiceImpl;
    }

    @Operation(summary = "Create a new product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Product created",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = products.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content) })
    @PostMapping
    public ResponseEntity<products> createProduct(
            @Parameter(description = "Product to create", required = true) @RequestBody products product) {
        products createdProduct = productServiceImpl.createProduct(product);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    @Operation(summary = "Find all products")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found all products",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = products.class)) }) })
    @GetMapping
    public ResponseEntity<List<products>> findAllProducts() {
        List<products> savedProducts = productServiceImpl.findAll();
        return new ResponseEntity<>(savedProducts, HttpStatus.CREATED);
    }

    @Operation(summary = "Delete a product if it is not associated with any cart")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product deleted successfully",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Product is associated with a cart and cannot be deleted",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Product not found",
                    content = @Content) })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(
            @Parameter(description = "ID of the product to delete", required = true) @PathVariable Long id) {
        try {
            productServiceImpl.deleteProduct(id);
            return new ResponseEntity<>("Product deleted successfully", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
