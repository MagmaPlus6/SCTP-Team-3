package org.generation.ProductsAPI.repository;

import org.generation.ProductsAPI.repository.entity.product;
import org.springframework.data.repository.CrudRepository;


// This will be AUTO IMPLEMENTED by Spring into a Bean called productRepository
// CRUD refers Create, Read, Update, Delete
public interface ProductRepository extends CrudRepository<product, Integer> {

}
