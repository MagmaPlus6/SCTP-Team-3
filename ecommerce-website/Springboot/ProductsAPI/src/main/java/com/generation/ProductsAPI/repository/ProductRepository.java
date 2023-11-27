package com.generation.ProductsAPI.repository;

import com.generation.ProductsAPI.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// This will be AUTO IMPLEMENTED by Spring into a Bean called productRepository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findByNameContaining(String productName);

    List<Product> findByBrandContaining(String brandName);

    List<Product> findByOrderByPriceAsc();

    List<Product> findByOrderByPriceDesc();

    List<Product> findByPriceBetween(Double startPrice, Double endPrice);

}
