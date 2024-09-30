package Ecommerce.example.Ecommerce;

import Ecommerce.example.Ecommerce.Entity.products;
import Ecommerce.example.Ecommerce.Repository.ProductRepository;
import Ecommerce.example.Ecommerce.ServiceImpl.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@ExtendWith(SpringExtension.class)
class ProductServiceImplTests {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductServiceImpl productService;

    @BeforeEach
    void setUp() {
        // Clean the database before each test
        productRepository.deleteAll();

        // Add sample products to the database
        products product1 = new products( "Product Name AAAAA");
        products product2 = new products( "Product Name BBBBB");

        productRepository.save(product1);
        productRepository.save(product2);
    }

    @Test
    void testFindAll() {
        // Call the service method
        List<products> productList = productService.findAll();

        // Assertions
        assertThat(productList).hasSize(2); // Check that two products are returned
    }
}
