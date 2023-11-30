package com.mrojasdev.tsystemstore.service;

import com.mrojasdev.tsystemstore.exception.OrderNotFoundException;
import com.mrojasdev.tsystemstore.model.Order;
import com.mrojasdev.tsystemstore.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    @Transactional
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    @Transactional
    public Order getOrderById(long orderId) {
        return orderRepository.findById(orderId).orElseThrow(
                () -> new OrderNotFoundException("order with id "+orderId+" not found")
        );
    }

    @Override
    @Transactional
    public void placeOrder(Order order) {
        orderRepository.save(order);
    }

    @Override
    @Transactional
    public void updateOrderStatus(long orderId, String status) {
        Optional<Order> existingOrder = orderRepository.findById(orderId);
        if(existingOrder.isPresent()){
            Order orderToUpdate = existingOrder.get();
            orderToUpdate.setOrderStatus(status);
            orderRepository.save(orderToUpdate);
        } else {
            throw new OrderNotFoundException("order doesn't exist in database");
        }
    }
}
