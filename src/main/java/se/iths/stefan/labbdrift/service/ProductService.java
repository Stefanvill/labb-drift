package se.iths.stefan.labbdrift.service;

import org.springframework.stereotype.Service;
import se.iths.stefan.labbdrift.exception.product.ProductNotFoundException;
import se.iths.stefan.labbdrift.model.Product;
import se.iths.stefan.labbdrift.repository.ProductRepository;
import se.iths.stefan.labbdrift.validator.ProductValidator;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductValidator productValidator;

    public ProductService(ProductRepository productRepository, ProductValidator productValidator) {
        this.productRepository = productRepository;
        this.productValidator = productValidator;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProduct(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product with id " + id + " not found"));
    }

    public Product createProduct(Product product) {
        productValidator.validate(product);
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, Product updated) {
        Product existing = getProduct(id);
        
        existing.setName(updated.getName());
        existing.setPrice(updated.getPrice());
        existing.setStock(updated.getStock());
        existing.setActive(updated.isActive());

        productValidator.validate(existing);

        return productRepository.save(existing);
    }

    public void deleteProduct(Long id) {
        Product existing = getProduct(id);
        productRepository.delete(existing);
    }
}

