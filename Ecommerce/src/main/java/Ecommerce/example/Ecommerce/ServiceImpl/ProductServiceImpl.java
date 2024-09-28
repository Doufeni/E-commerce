package Ecommerce.example.Ecommerce.ServiceImpl;

import Ecommerce.example.Ecommerce.Entity.products;
import Ecommerce.example.Ecommerce.Repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ProductServiceImpl {

    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<products> findAll() {
        Iterable<products> savedProducts = productRepository.findAll();
        return StreamSupport
                .stream(savedProducts.spliterator(), false)
                .collect(Collectors.toList());

    }
}
