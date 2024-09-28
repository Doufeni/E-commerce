package Ecommerce.example.Ecommerce.Controller;


import Ecommerce.example.Ecommerce.Entity.products;
import Ecommerce.example.Ecommerce.ServiceImpl.ProductServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/products")
public class ProductController {

    private ProductServiceImpl productServiceImpl;
    public ProductController(ProductServiceImpl productServiceImpl) {

        this.productServiceImpl = productServiceImpl;
    }


    @GetMapping
    public ResponseEntity<List<products>> findAllProducts() {
        List<products> savedProducts = productServiceImpl.findAll();
        return new ResponseEntity<>(savedProducts, HttpStatus.CREATED);
    }
}
