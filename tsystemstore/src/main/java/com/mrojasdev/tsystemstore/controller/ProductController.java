package com.mrojasdev.tsystemstore.controller;

import com.mrojasdev.tsystemstore.model.Client;
import com.mrojasdev.tsystemstore.model.Product;
import com.mrojasdev.tsystemstore.model.Product;
import com.mrojasdev.tsystemstore.model.ProductDTO;
import com.mrojasdev.tsystemstore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/products")
public class ProductController {
    
    private final ProductService productService;
    
    @Autowired
    public ProductController(ProductService productService) {this.productService = productService; }

    @GetMapping
    public List<ProductDTO> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ProductDTO getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @PostMapping
    public String saveProduct(@ModelAttribute("product") Product product) {
        productService.addProduct(product);
        return "redirect:/admin/list-products";
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateProduct(@PathVariable Long id, @RequestBody Product updatedProduct) {
        productService.updateProduct(id, updatedProduct);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/category/{category}")
    public List<ProductDTO> getProductsOfCategory(@PathVariable String category) {
        return productService.getProductsOfCategory(category);
    }

    @GetMapping("/color/{color}")
    public List<ProductDTO> getProductsOfColor(@PathVariable String color) {
        return productService.getProductsOfColor(color);
    }

    @GetMapping("/brand/{brand}")
    public List<ProductDTO> getProductsOfBrand(@PathVariable String brand) {
        return productService.getProductsOfBrand(brand);
    }

    @PostMapping("/update/{id}")
    public String updateProductInfo(@PathVariable Long id, @ModelAttribute("product") Product updatedProduct) {
        productService.updateProduct(id, updatedProduct);
        return "redirect:/admin/list-products";
    }
    
    
}
