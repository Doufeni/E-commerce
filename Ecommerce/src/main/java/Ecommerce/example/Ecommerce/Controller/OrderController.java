package Ecommerce.example.Ecommerce.Controller;


import Ecommerce.example.Ecommerce.Entity.CustomerOrder;
import Ecommerce.example.Ecommerce.ServiceImpl.OrderServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    public OrderServiceImpl orderServiceImpl;

    @Operation(summary = "Get all orders")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of orders",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomerOrder.class)) }) })
    @GetMapping
    public ResponseEntity<List<CustomerOrder>> findAllOrders() {
        List<CustomerOrder> orders = orderServiceImpl.findAllOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @PostMapping("/create-from-cart/{customerId}")
    public ResponseEntity<CustomerOrder> createOrderFromCart(@PathVariable Long customerId) {
        CustomerOrder createdOrder = orderServiceImpl.createOrder(customerId);
        return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
    }
}
