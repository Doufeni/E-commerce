package Ecommerce.example.Ecommerce;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import Ecommerce.example.Ecommerce.Controller.ProductController;
import Ecommerce.example.Ecommerce.Entity.products;
import Ecommerce.example.Ecommerce.ServiceImpl.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private ProductServiceImpl productServiceImpl;

    private List<products> productList;

    @BeforeEach
    void setUp() {
        productList = new ArrayList<>();
        products product = new products();
        productList.add(product);
    }

    @Test
    void testFindAllProducts() throws Exception {
        // Mock the service layer response
        when(productServiceImpl.findAll()).thenReturn(productList);

        // Perform the GET request and verify the response
        mockMvc.perform(get("/api/v1/products"))
                .andExpect(status().isCreated()); // Expecting HTTP 201 status
        }
}
