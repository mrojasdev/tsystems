package com.mrojasdev.tsystemstore.service;

import com.mrojasdev.tsystemstore.exception.ProductNotFoundException;
import com.mrojasdev.tsystemstore.mapper.ProductMapper;
import com.mrojasdev.tsystemstore.model.OrderProduct;
import com.mrojasdev.tsystemstore.model.Product;
import com.mrojasdev.tsystemstore.model.ProductDTO;
import com.mrojasdev.tsystemstore.model.TopProductDTO;
import com.mrojasdev.tsystemstore.repository.OrderProductRepository;
import com.mrojasdev.tsystemstore.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private OrderProductRepository orderProductRepository;

    @Override
    @Transactional
    public List<ProductDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return productMapper.productsToProductsDTO(products);
    }

    @Override
    @Transactional
    public ProductDTO getProductById(long productId) {
        Product product = productRepository.findById(productId).orElseThrow(
                () -> new ProductNotFoundException("Product with id "+productId+" not found")
        );
        return productMapper.productToProductDTO(product);
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

    @Override
    @Transactional
    public List<ProductDTO> getProductsOfCategory(String category){
        List<Product> products = productRepository.findAll();
        products = products.stream().filter(product -> product.getCategory().equals(category)).toList();
        return productMapper.productsToProductsDTO(products);
    }

    @Override
    @Transactional
    public List<ProductDTO> getProductsOfColor(String color){
        List<Product> products = productRepository.findAll();
        products = products.stream().filter(product -> product.getColor().equals(color)).toList();
        return productMapper.productsToProductsDTO(products);
    }

    @Override
    @Transactional
    public List<ProductDTO> getProductsOfBrand(String brand){
        List<Product> products = productRepository.findAll();
        products = products.stream().filter(product -> product.getBrand().equals(brand)).toList();
        return productMapper.productsToProductsDTO(products);
    }

    @Override
    @Transactional
    public List<TopProductDTO> getTopTenProductsMostSold() {
        List<OrderProduct> allOrderProducts = orderProductRepository.findAll();

        Map<Product, Long> totalQuantityByProduct = allOrderProducts.stream()
                .collect(Collectors.groupingBy(OrderProduct::getProduct, Collectors.summingLong(OrderProduct::getQuantity)));

        return totalQuantityByProduct.entrySet().stream()
                .sorted((entry1, entry2) -> Long.compare(entry2.getValue(), entry1.getValue()))
                .limit(10)
                .map(entry -> new TopProductDTO(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<String> getAllCategories() {
        List<String> categories = productRepository.findAll().stream()
                .map(Product::getCategory)
                .distinct()
                .collect(Collectors.toList());
        return categories;
    }


}
