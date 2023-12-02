package com.mrojasdev.tsystemstore.service;

import com.mrojasdev.tsystemstore.model.Product;
import com.mrojasdev.tsystemstore.model.ProductDTO;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<ProductDTO> getAllProducts();
    ProductDTO getProductById(long productId);
    void addProduct(Product product);
    void updateProduct(long productId, Product product);
    void deleteProduct(long productId);
}
