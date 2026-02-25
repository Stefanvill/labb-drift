package se.iths.stefan.labbdrift.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.iths.stefan.labbdrift.exception.product.ProductNotFoundException;
import se.iths.stefan.labbdrift.model.Product;
import se.iths.stefan.labbdrift.repository.ProductRepository;
import se.iths.stefan.labbdrift.validator.ProductValidator;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @InjectMocks
    private ProductService productService;
    @Mock
    private ProductRepository productRepository;
    @Mock
    private ProductValidator productValidator;

    private Product product;

    @BeforeEach
    void setUp() {
        product = new Product();
        product.setId(1L);
        product.setName("Milk");
        product.setPrice(10);
        product.setStock(5);
        product.setActive(true);
    }

    @Test
    void getAllProductsReturnsProductList() {
        when(productRepository.findAll()).thenReturn(List.of(product));

        List<Product> result = productService.getAllProducts();

        assertEquals(1, result.size());
        assertEquals("Milk", result.get(0).getName());
        verify(productRepository).findAll();
    }

    @Test
    void getProductByIdReturnsProduct() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        Product result = productService.getProduct(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Milk", result.getName());
        verify(productRepository).findById(1L);
        assertDoesNotThrow(() -> productService.getProduct(1L));
    }

    @Test
    void getProductByIdReturnsProductNotFoundException() {
        when(productRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class, () -> productService.getProduct(99L));
        verify(productRepository).findById(99L);
    }

    @Test
    void createAndSaveProductVerifyValidatorAndRepository() {
        when(productRepository.save(product)).thenReturn(product);

        Product result = productService.createProduct(product);

        assertNotNull(result);
        assertEquals("Milk", result.getName());
        verify(productValidator).validate(product);
        verify(productRepository).save(product);
    }

    @Test
    void deleteProductByIdAndVerify() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        productService.deleteProduct(1L);

        verify(productRepository).findById(1L);
        verify(productRepository).delete(product);
    }

    @Test
    void updateProductVerifyValidatorAndRepository() {
        Product existing = new Product();
        existing.setId(2L);
        existing.setName("Old");
        existing.setPrice(1);
        existing.setStock(1);
        existing.setActive(false);

        Product updated = new Product();
        updated.setName("New");
        updated.setPrice(99);
        updated.setStock(10);
        updated.setActive(true);

        when(productRepository.findById(2L)).thenReturn(Optional.of(existing));
        when(productRepository.save(any(Product.class))).thenAnswer(inv -> inv.getArgument(0));

        Product result = productService.updateProduct(2L, updated);

        assertEquals(2L, result.getId());
        assertEquals("New", result.getName());
        assertEquals(99, result.getPrice());
        assertEquals(10, result.getStock());
        assertTrue(result.isActive());
        
        verify(productValidator).validate(existing);
        verify(productRepository).save(existing);
    }
}