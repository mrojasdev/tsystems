package com.mrojasdev.tsystemstore.controller;

import com.mrojasdev.tsystemstore.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/addToCart/{productId}")
    public String addToCart(@PathVariable(name = "productId") long productId) {
        cartService.addToCart(productId);
        return "redirect:/products";
    }

}
