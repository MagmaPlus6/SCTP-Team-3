package com.generation.ProductsAPI.controller;

import com.generation.ProductsAPI.exception.ResourceNotFoundException;
import com.generation.ProductsAPI.model.Product;
import com.generation.ProductsAPI.service.ImageService;
import com.generation.ProductsAPI.service.ImageServiceImpl;
import com.generation.ProductsAPI.service.ProductService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/products")
public class ProductServiceController {

    ProductService productService;

    ImageService imageService;

    public ProductServiceController(@Autowired ProductService productService, @Autowired ImageService imageService){
        this.productService = productService;
        this.imageService = imageService;
    }

    @GetMapping
    public ResponseEntity<Object> getAllProducts() {

        List<Product> result = productService.getAllProducts();

        if(result.isEmpty()) {
            throw new ResourceNotFoundException("No product(s) found");
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getSingleProduct(@PathVariable Integer id){

        Product result = productService.getProduct(id).orElseThrow(() -> new ResourceNotFoundException("Product of id " + id + " is not found"));

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/sortby_price_asc")
    public ResponseEntity<Object> sortAllProductsByPriceAscend() {

        List<Product> result = productService.sortAllProductsByPriceAscend();

        if(result.isEmpty()) {
            throw new ResourceNotFoundException("No product(s) found");
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/sortby_price_desc")
    public ResponseEntity<Object> sortAllProductsByPriceDescend() {

        List<Product> result = productService.sortAllProductsByPriceDescend();

        if(result.isEmpty()) {
            throw new ResourceNotFoundException("No product(s) found");
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

//    @GetMapping("/name/{productName}")
//    public ResponseEntity<Object> getProductsWithName(@PathVariable String productName) {
//
//        List<Product> result = productService.getProductsWithName(productName);
//
//        if(result.isEmpty()) {
//            throw new ResourceNotFoundException("There are no product(s) with name " + productName);
//        }
//
//        return new ResponseEntity<>(result, HttpStatus.OK);
//    }

    @GetMapping("/brand/{brandName}")
    public ResponseEntity<Object> getProductsWithBrand(@PathVariable String brandName) {

        List<Product> result = productService.getProductsWithBrand(brandName);

        if(result.isEmpty()) {
            throw new ResourceNotFoundException("There are no product(s) with brand " + brandName);
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

//    @GetMapping("/")
//    public ResponseEntity<List<Product>> searchforProducts(@RequestParam String name, @RequestParam String brand) {
//
//        List<Product> result = productService.searchProducts(name, brand);
//
//        if(result.isEmpty()) {
//            throw new ResourceNotFoundException("No product(s) found");
//        }
//
//        return new ResponseEntity<>(result, HttpStatus.OK);
//    }

    @GetMapping("/")
    public ResponseEntity<List<Product>> searchProducts(@RequestParam String name) {

        List<Product> result = productService.getProductsWithName(name);

        if(result.isEmpty()) {
            throw new ResourceNotFoundException("There are no product(s) with name " + name);
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Object> createNewProduct(@Valid @RequestBody Product product) {

        return new ResponseEntity<>(productService.createNewProduct(product), HttpStatus.CREATED);
    }

//    @PostMapping()
//    public ResponseEntity<Object> createNewProduct(@Valid @RequestBody Product product, MultipartFile imageFile) throws IOException{
//
//        String newImageName = imageService.uploadImageToFileSystem(imageFile);
//
//        return new ResponseEntity<>(productService.createNewProduct(product, newImageName), HttpStatus.CREATED);
//    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable Integer id ,@Valid @RequestBody Product product) {

        Product result = productService.updateProduct(id, product).orElseThrow(() -> new ResourceNotFoundException("Product of id " + id + " is not found"));;

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Integer id) throws IOException {

        Product result = productService.getProduct(id).orElseThrow(() -> new ResourceNotFoundException("Product of id " + id + " is not found"));

        String imageToDelete = result.getImage();

        productService.deleteProduct(id);
        imageService.deleteImage(imageToDelete);

        return new ResponseEntity<>("Product is deleted successfully.", HttpStatus.OK);
    }

}
