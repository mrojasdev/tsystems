package com.mrojasdev.tsystemstore.repository;

import com.mrojasdev.tsystemstore.model.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long> {

    Product findById(long id);

    List<Product> findByCategory(String category);

    List<Product> findByColor(String color);

}
