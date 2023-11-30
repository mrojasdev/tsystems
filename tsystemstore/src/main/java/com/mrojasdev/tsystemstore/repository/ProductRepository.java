package com.mrojasdev.tsystemstore.repository;

import com.mrojasdev.tsystemstore.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByCategory(String category);

    List<Product> findByColor(String color);

}
