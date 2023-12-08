package com.mrojasdev.tsystemstore.controller;

import com.mrojasdev.tsystemstore.model.*;
import com.mrojasdev.tsystemstore.service.CartService;
import com.mrojasdev.tsystemstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/api/orders")
public class OrderController {
    
    private final OrderService orderService;

    private CartService cartService;
    
    @Autowired
    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    @GetMapping
    public List<OrderDTO> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public OrderDTO getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }

    @PostMapping
    public String saveOrder(@ModelAttribute("order") Order order) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Client client = null;
        if(principal instanceof Client){
            client = ((Client) principal);
        }
        order.setClient(client);
        order.setOrderDate(LocalDate.now());
        orderService.placeOrder(order);
        cartService.checkoutCart(client, order);
        return "redirect:/";
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateOrderStatus(@PathVariable Long id, @RequestBody String status) {
        orderService.updateOrderStatus(id, status);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}/products")
    public List<ProductDTO> listOrderProducts(@PathVariable Long id){
        return orderService.listOrderProducts(id);
    }


}
