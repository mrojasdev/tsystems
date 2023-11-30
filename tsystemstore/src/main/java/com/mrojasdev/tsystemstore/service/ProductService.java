package com.mrojasdev.tsystemstore.service;

import com.mrojasdev.tsystemstore.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> getAllProducts();
    Product getProductById(long productId);
    void addProduct(Product product);
    void updateProduct(long productId, Product product);
    void deleteProduct(long productId);
}
