package com.generation.ProductsAPI.service;

import com.generation.ProductsAPI.model.Product;
import com.generation.ProductsAPI.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;


// ProductServiceImpl testing tests for persistence of data
@ExtendWith(MockitoExtension.class) //Use the Mokito framework to create and inject mocked objects into our JUnit tests
class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepo;

    @InjectMocks
    private ProductServiceImpl productServiceImpl;

    private Product product1, product2, product3, updatedProduct;

    @BeforeEach // @BeforeEach annotation initialises init() function before each test (@Test) is run
    void init(){
        product1 = new Product("Grey casual shirt", "Tommy Hilfiger", new BigDecimal("50.10"), "casual-shirt.jpg");
        product1.setId(1);
        product2 = new Product("Blue khakis", "Adidas", new BigDecimal("27.83"), "blue-khakis.jpg");
        product2.setId(2);
        product3 = new Product("Grey belt", "Adidas", new BigDecimal("11"), "grey-belt.jpg");
        product3.setId(3);
        updatedProduct = new Product("White casual shirt", "Tommy Hilfiger", new BigDecimal("50.10"), "casual-shirt.jpg");
        updatedProduct.setId(1);
    }

    @Test
    void createNewProduct() {

        when(productRepo.save(any(Product.class))).thenReturn(product1);

        Product newProduct = productServiceImpl.createNewProduct(product1);

        assertNotNull(newProduct);
        assertThat(newProduct.getName()).isEqualTo("Grey casual shirt");
        assertThat(newProduct.getName()).isNotEqualTo("Blue khakis");
    }

//    @Test
//    void createNewProduct() {
//
//        when(productRepo.save(any(Product.class))).thenReturn(product1);
//
//        Product newProduct = productServiceImpl.createNewProduct(product1, any(String.class));
//
//        assertNotNull(newProduct);
//        assertThat(newProduct.getName()).isEqualTo("Grey casual shirt");
//        assertThat(newProduct.getName()).isNotEqualTo("Blue khakis");
//    }

    @Test
    void updateProduct() {

        when(productRepo.findById(any(Integer.class))).thenReturn(Optional.ofNullable(product1));
        when(productRepo.save(any(Product.class))).thenReturn(product1);

        String originalName = product1.getName();
        product1.setName("White casual shirt");
        Optional<Product> currentProduct = productServiceImpl.updateProduct(product1.getId(), product1);

        assertNotNull(currentProduct);
        assertEquals(updatedProduct.getName(), currentProduct.get().getName());
        assertNotEquals(originalName, currentProduct.get().getName());
    }

    @Test
    void deleteProduct() {

        doNothing().when(productRepo).deleteById(any(Integer.class));

        productServiceImpl.deleteProduct(product1.getId());

        verify(productRepo, times(1)).deleteById(product1.getId());

    }

    @Test
    void getAllProducts() {

        List<Product> list = new ArrayList<>();
        list.add(product1);
        list.add(product2);

        when(productRepo.findAll()).thenReturn(list);
        List<Product> results = productServiceImpl.getAllProducts();

        assertNotNull(results);
        assertEquals(2, results.size());
        assertThat(results).containsExactlyInAnyOrder(product1, product2);
    }

    @Test
    void sortAllProductsByPriceAscend() {

        List<Product> list = new ArrayList<>();
        list.add(product3);
        list.add(product2);
        list.add(product1);

        when(productRepo.findByOrderByPriceAsc()).thenReturn(list);

        List<Product> results = productServiceImpl.sortAllProductsByPriceAscend();

        assertNotNull(results);
        assertEquals(3, results.size());
        assertThat(results).containsExactly(product3,product2,product1);
    }

    @Test
    void sortAllProductsByPriceDescend() {

        List<Product> list = new ArrayList<>();
        list.add(product1);
        list.add(product2);
        list.add(product3);

        when(productRepo.findByOrderByPriceDesc()).thenReturn(list);

        List<Product> results = productServiceImpl.sortAllProductsByPriceDescend();

        assertNotNull(results);
        assertEquals(3, results.size());
        assertThat(results).containsExactly(product1,product2,product3);
    }

    @Test
    void getProduct() {

        when(productRepo.findById(any(Integer.class))).thenReturn(Optional.of(product1));

        Optional<Product> currentProduct = productServiceImpl.getProduct(product1.getId());

        assertNotNull(currentProduct);
        assertFalse(currentProduct.isEmpty());
        assertThat(currentProduct.get().getId()).isEqualTo(1);
    }

    @Test
    void getProductsWithName() {

        List<Product> list = new ArrayList<>();
        list.add(product1);
        list.add(product3);

        when(productRepo.findByNameContaining("Grey")).thenReturn(list);

        List<Product> results = productServiceImpl.getProductsWithName("Grey");

        assertNotNull(results);
        assertEquals(2, results.size());
        assertThat(results).containsExactlyInAnyOrder(product3, product1);
    }

    @Test
    void getProductsWithBrand() {

        List<Product> list = new ArrayList<>();
        list.add(product2);
        list.add(product3);

        when(productRepo.findByBrandContaining("Adidas")).thenReturn(list);

        List<Product> results = productServiceImpl.getProductsWithBrand("Adidas");

        assertNotNull(results);
        assertEquals(2, results.size());
        assertThat(results).containsExactlyInAnyOrder(product2, product3);
    }
}