package com.mrojasdev.tsystemstore.service;

import com.mrojasdev.tsystemstore.model.Order;
import com.mrojasdev.tsystemstore.model.OrderDTO;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    List<OrderDTO> getAllOrders();
    OrderDTO getOrderById(long orderId);
    void placeOrder(Order order);
    void updateOrderStatus(long orderId, String status);
}
