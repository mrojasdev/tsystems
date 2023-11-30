package com.mrojasdev.tsystemstore.service;

import com.mrojasdev.tsystemstore.exception.ProductNotFoundException;
import com.mrojasdev.tsystemstore.model.Product;
import com.mrojasdev.tsystemstore.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    @Transactional
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    @Transactional
    public Product getProductById(long productId) {
        return productRepository.findById(productId).orElseThrow(
                () -> new ProductNotFoundException("Product with id "+productId+" not found")
        );
    }

    @Override
    @Transactional
    public void addProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    @Transactional
    public void updateProduct(long productId, Product updatedProduct) {
        Optional<Product> existingProduct = productRepository.findById(productId);
        if(existingProduct.isPresent()){
            Product productToUpdate = existingProduct.get();
            productToUpdate.setTitle(updatedProduct.getTitle());
            productToUpdate.setBrand(updatedProduct.getBrand());
            productToUpdate.setCategory(updatedProduct.getCategory());
            productToUpdate.setBrand(updatedProduct.getBrand());
            productToUpdate.setColor(updatedProduct.getColor());
            productToUpdate.setStock(updatedProduct.getStock());
            productToUpdate.setPrice(updatedProduct.getPrice());
            productToUpdate.setVolume(updatedProduct.getVolume());
            productToUpdate.setWeight(updatedProduct.getWeight());
            productRepository.save(productToUpdate);
        } else {
            throw new ProductNotFoundException("product doesn't exist in database");
        }
    }

    @Override
    @Transactional
    public void deleteProduct(long productId) {
        productRepository.deleteById(productId);
    }
}