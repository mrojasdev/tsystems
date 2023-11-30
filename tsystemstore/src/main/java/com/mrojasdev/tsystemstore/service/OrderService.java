package com.mrojasdev.tsystemstore.service;

import com.mrojasdev.tsystemstore.model.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    List<Order> getAllOrders();
    Order getOrderById(long orderId);
    void placeOrder(Order order);
    void updateOrderStatus(long orderId, String status);
}
