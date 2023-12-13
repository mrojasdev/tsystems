package com.mrojasdev.tsystemstore.service;

import com.mrojasdev.tsystemstore.model.*;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    List<OrderDTO> getAllOrders();
    OrderDTO getOrderById(long orderId);
    Order placeOrder(Order order);
    void updateOrderStatus(long orderId, String status);
    void updateOrderPaymentStatus(long orderId, String status);
    List<ProductDTO> listOrderProducts(long orderId);
    List<OrderDTO> getOrdersDTObyClient(Client client);
    List<Order> getOrdersByClient(Client client);
}
