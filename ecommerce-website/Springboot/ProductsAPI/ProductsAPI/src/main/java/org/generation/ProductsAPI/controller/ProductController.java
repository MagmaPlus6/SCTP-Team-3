package org.generation.ProductsAPI.controller;

import org.generation.ProductsAPI.repository.ProductRepository;
import org.generation.ProductsAPI.repository.entity.product;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/product")

public class ProductController {

    final ProductRepository productRepository;

//    @PostMapping(path="/add") // Map ONLY POST Requests
//    public @ResponseBody String addNewProduct (@RequestParam String name
//            , @RequestParam String brand, @RequestParam Double price, @RequestParam String image_link) {
//        // @ResponseBody means the returned String is the response, not a view name
//        // @RequestParam means it is a parameter from the GET or POST request
//
//        product n = new product();
//        n.setName(name);
//        n.setBrand(brand);
//        n.setPrice(price);
//        n.setImageLink(image_link);
//        productRepository.save(n);
//        return "Saved";
//    }
    public ProductController(@Autowired ProductRepository productRepository )
    {
        this.productRepository = productRepository;
    }

    @GetMapping
    public Iterable<product> getProducts() {
        // This returns a JSON or XML with the users
        return productRepository.findAll();
    }

}
