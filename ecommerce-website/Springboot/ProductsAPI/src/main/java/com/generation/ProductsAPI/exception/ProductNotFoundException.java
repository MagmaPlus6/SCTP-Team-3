package com.generation.ProductsAPI.exception;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException() {
        super("No product(s) found");
    }

}
