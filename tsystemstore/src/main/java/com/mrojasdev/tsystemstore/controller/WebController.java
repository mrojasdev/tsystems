package com.mrojasdev.tsystemstore.controller;

import com.mrojasdev.tsystemstore.mapper.ClientAddressMapper;
import com.mrojasdev.tsystemstore.mapper.ClientMapper;
import com.mrojasdev.tsystemstore.mapper.OrderMapper;
import com.mrojasdev.tsystemstore.mapper.ProductMapper;
import com.mrojasdev.tsystemstore.repository.ClientAddressRepository;
import com.mrojasdev.tsystemstore.repository.ClientRepository;
import com.mrojasdev.tsystemstore.repository.OrderRepository;
import com.mrojasdev.tsystemstore.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping()
public class WebController {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ClientAddressRepository clientAddressRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ClientMapper clientMapper;

    @Autowired
    private ClientAddressMapper clientAddressMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private ProductMapper productMapper;


    @GetMapping("/")
    public String getHome() {
        return "index";
    }

    @GetMapping("/products")
    public String getProducts(Model model) {
        model.addAttribute("products", productMapper.productsToProductsDTO(productRepository.findAll()));
        return "products";
    }


}
