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

    public products createProduct(products product) {
        return productRepository.save(product);
    }

    public void deleteProduct(Long productId) {
        products product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        if (!product.getCarts().isEmpty()) {
            throw new RuntimeException("Product is associated with one or more carts and cannot be deleted.");
        }

        productRepository.delete(product);
    }

}
