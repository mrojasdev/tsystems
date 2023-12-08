package com.mrojasdev.tsystemstore.service;

import com.mrojasdev.tsystemstore.model.*;
import com.mrojasdev.tsystemstore.repository.CartRepository;
import com.mrojasdev.tsystemstore.repository.OrderProductRepository;
import com.mrojasdev.tsystemstore.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderProductRepository orderProductRepository;


    @Override
    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }

    @Override
    public void addToCart(long productId) {
        Product product = productRepository.findById(productId).get();
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Client client = null;
        if(principal instanceof Client){
            client = ((Client) principal);
        }
        Cart cart = new Cart();
        cart.setProduct(product);
        cart.setClient(client);
        cartRepository.save(cart);
    }

    public void checkoutCart(Client client, Order order) {
        List<Cart> cartList = cartRepository.findByClient(client);
        ArrayList<String> productList = new ArrayList<>();
        cartList.stream().forEach(item -> productList.add(item.getProduct().getId().toString()));
        Map<String, Long> map = productList.stream()
                .collect(Collectors.groupingBy(s -> s, Collectors.counting()));
        for(Map.Entry<String, Long> entry : map.entrySet()) {
            String productId = entry.getKey();
            Long count = entry.getValue();
            OrderProduct orderProduct = new OrderProduct();
            orderProduct.setProduct(productRepository.findById(Long.parseLong(productId)).get());
            orderProduct.setQuantity(Long.valueOf(count).intValue());

            orderProduct.setOrder(order);
            orderProductRepository.save(orderProduct);
        }
    }
}
