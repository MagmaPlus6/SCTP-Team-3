package org.generation.ProductsAPI;

import org.generation.ProductsAPI.controller.ProductController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackageClasses= ProductController.class)
public class ProductsApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductsApiApplication.class, args);

	}

}
