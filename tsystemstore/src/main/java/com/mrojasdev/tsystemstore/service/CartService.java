package com.mrojasdev.tsystemstore.service;

import com.mrojasdev.tsystemstore.model.*;

import java.util.List;

public interface CartService {

    List<Cart> getAllCarts();
    void addToCart(long productId);
    void checkoutCart(Client client, Order order);
    int cartItemCount(Client client);

    List<OrderProduct> getCartSummary(Client client);
}
