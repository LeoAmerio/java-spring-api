package com.aplication.springboot.spring.restfullapi.repositories;

import com.aplication.springboot.spring.restfullapi.entities.Product;
import org.springframework.data.repository.CrudRepository;

public interface IProductRepository extends CrudRepository<Product, Long> {

    boolean existsBySku(String sku);
}
