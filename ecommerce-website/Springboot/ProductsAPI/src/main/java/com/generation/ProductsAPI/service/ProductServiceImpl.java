package com.generation.ProductsAPI.service;

import com.generation.ProductsAPI.model.Product;
import com.generation.ProductsAPI.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(@Autowired ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product createNewProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Optional<Product> updateProduct(Integer id, Product product) {

        Optional<Product> result = productRepository.findById(id);

        try{
            Product temp = result.get();
            temp.setName(product.getName());
            temp.setBrand(product.getBrand());
            temp.setPrice(product.getPrice());
            temp.setImage(product.getImage());
            return Optional.of(productRepository.save(temp));
        }catch(Exception e){
            return result;
        }
    }

    @Override
    public void deleteProduct(Integer id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> sortAllProductsByPriceAscend() {
        return productRepository.findByOrderByPriceAsc();
    }

    @Override
    public List<Product> sortAllProductsByPriceDescend() {
        return productRepository.findByOrderByPriceDesc();
    }

    @Override
    public Optional<Product> getProduct(Integer id) {
        return productRepository.findById(id);
    }

    @Override
    public List<Product> getProductsWithName(String productName) {
        return productRepository.findByNameContaining(productName);
    }

    @Override
    public List<Product> getProductsWithBrand(String productBrand) {
        return productRepository.findByBrandContaining(productBrand);
    }

    @Override
    public List<Product> searchProducts(String productName, String productBrand) {
        return productRepository.findByNameContainingOrBrandContaining(productName, productBrand);
    }

}
