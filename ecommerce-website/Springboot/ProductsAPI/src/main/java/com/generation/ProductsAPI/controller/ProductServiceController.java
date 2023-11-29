package com.generation.ProductsAPI.controller;

import com.generation.ProductsAPI.exception.ProductNotFoundException;
import com.generation.ProductsAPI.model.Product;
import com.generation.ProductsAPI.service.ProductService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/products")
public class ProductServiceController {

    ProductService productService;

    public ProductServiceController(@Autowired ProductService productService){
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<Object> getAllProducts() {

        List<Product> result = productService.getAllProducts();

        if(result.isEmpty()) {
            throw new ProductNotFoundException();
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getSingleProduct(@PathVariable Integer id){

        Product result = productService.getProduct(id).orElseThrow(() -> new ProductNotFoundException());

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/sortby_price_asc")
    public ResponseEntity<Object> sortAllProductsByPriceAscend() {

        List<Product> result = productService.sortAllProductsByPriceAscend();

        if(result.isEmpty()) {
            throw new ProductNotFoundException();
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/sortby_price_desc")
    public ResponseEntity<Object> sortAllProductsByPriceDescend() {

        List<Product> result = productService.sortAllProductsByPriceDescend();

        if(result.isEmpty()) {
            throw new ProductNotFoundException();
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/name/{productName}")
    public ResponseEntity<Object> getProductsWithName(@PathVariable String productName) {

        List<Product> result = productService.getProductsWithName(productName);

        if(result.isEmpty()) {
            throw new ProductNotFoundException();
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/brand/{brandName}")
    public ResponseEntity<Object> getProductsWithBrand(@PathVariable String brandName) {

        List<Product> result = productService.getProductsWithBrand(brandName);

        if(result.isEmpty()) {
            throw new ProductNotFoundException();
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @PostMapping()
    public ResponseEntity<Object> createNewProduct(@Valid @RequestBody Product product) {

        return new ResponseEntity<>(productService.createNewProduct(product), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable Integer id ,@Valid @RequestBody Product product) {

        Product result = productService.updateProduct(id, product).orElseThrow(() -> new ProductNotFoundException());;

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Integer id) {

        productService.getProduct(id).orElseThrow(() -> new ProductNotFoundException());

        productService.deleteProduct(id);

        return new ResponseEntity<>("Product is deleted successfully.", HttpStatus.OK);
    }


}
