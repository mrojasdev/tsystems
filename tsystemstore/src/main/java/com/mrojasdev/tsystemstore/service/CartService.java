package com.mrojasdev.tsystemstore.service;

import com.mrojasdev.tsystemstore.model.Cart;
import com.mrojasdev.tsystemstore.model.Client;
import com.mrojasdev.tsystemstore.model.Order;

import java.util.List;

public interface CartService {

    List<Cart> getAllCarts();
    void addToCart(long productId);
    void checkoutCart(Client client, Order order);
}
