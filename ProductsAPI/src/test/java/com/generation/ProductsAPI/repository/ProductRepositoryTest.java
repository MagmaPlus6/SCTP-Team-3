package com.generation.ProductsAPI.repository;

import com.generation.ProductsAPI.model.Product;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    private Product product1, product2, product3;

    @BeforeEach
    void init(){
        product1 = new Product("Grey test product 1", "Test Brand 1", new BigDecimal("0.10"), "test-product-1.jpg");

        product2 = new Product("Blue test product 2", "Test Brand 2", new BigDecimal("100.23"), "test-product-2.jpg");

        product3 = new Product("Grey test product 3", "Test Brand 2", new BigDecimal("11"), "test-product-3.jpg");
    }

    @Test
    void saveProduct() {
        // When : Action of behavious that we are going to test
        Product saveProduct = productRepository.save(product1);

        // Then : Verify the output
        assertNotNull(saveProduct);
        assertThat(saveProduct.getId()).isGreaterThan(0);
    }

    @Test
    void findAllProduct() {
        // Given : Setup object or precondition
        productRepository.save(product1);
        productRepository.save(product2);

        // When : Action of behavious that we are going to test
        List<Product> products = productRepository.findAll();

        // Then : Verify the output
        assertFalse(products.isEmpty());
        assertThat(products.size()).isGreaterThan(0);
    }

    @Test
    void findByProductId() {
        // Given : Setup object or precondition
        productRepository.save(product1);

        // When : Action of behavious that we are going to test
        Optional<Product> getProduct = productRepository.findById(product1.getId());

        // Then : Verify the output
        assertNotNull(getProduct);
    }

    @Test
    void findByNameContaining() {
        // Given : Setup object or precondition
        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);

        // When : Action of behavious that we are going to test
        List greyProducts = productRepository.findByNameContaining("Grey");

        // Then : Verify the output
        assertNotNull(greyProducts);
        assertEquals(2, greyProducts.size());
    }

    @Test
    void findByBrandContaining() {
        // Given : Setup object or precondition
        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);

        // When : Action of behavious that we are going to test
        List sortbyBrandProducts = productRepository.findByBrandContaining("Test Brand 2");

        // Then : Verify the output
        assertNotNull(sortbyBrandProducts);
        assertEquals(2, sortbyBrandProducts.size());
    }

    @Test
    void findByOrderByPriceAsc() {
        // Given : Setup object or precondition
        productRepository.save(product1);
        productRepository.save(product2);

        // When : Action of behavious that we are going to test
        List ascProducts = productRepository.findByOrderByPriceAsc();

        // Then : Verify the output
        assertNotNull(ascProducts);
        assertThat(ascProducts.size()).isGreaterThan(0);
        assertEquals(product1, ascProducts.get(0));
        assertEquals(product2, ascProducts.get(ascProducts.size()-1));
    }

    @Test
    void findByOrderByPriceDesc() {
        // Given : Setup object or precondition
        productRepository.save(product1);
        productRepository.save(product2);

        // When : Action of behavious that we are going to test
        List descProducts = productRepository.findByOrderByPriceDesc();

        // Then : Verify the output
        assertNotNull(descProducts);
        assertThat(descProducts.size()).isGreaterThan(0);
        assertEquals(product2, descProducts.get(0));
        assertEquals(product1, descProducts.get(descProducts.size()-1));
    }

    @Test
    void updateProduct() {
        // Given: Setup object or precondition
        productRepository.save(product1);

        // When: Action or behavior that we are going to test
        Product productToUpdateName = productRepository.findById(product1.getId()).get();
        productToUpdateName.setName("White test product 1");

        Product updatedProduct = productRepository.save(productToUpdateName);

        // Then: Verify the output or expected result
        assertNotNull(updatedProduct);
        assertEquals("White test product 1", updatedProduct.getName());
    }

    @Test
    void deleteProduct() {
        // Given: Setup object or precondition
        productRepository.save(product1);

        // When: Action or behavior that we are going to test
        productRepository.deleteById(product1.getId());
        Optional<Product> getProduct1 = productRepository.findById(product1.getId());

        // Then: Verify the output or expected result
        assertThat(getProduct1).isEmpty();
    }

}