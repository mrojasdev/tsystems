package com.mrojasdev.tsystemstore.service;

import com.mrojasdev.tsystemstore.exception.OrderNotFoundException;
import com.mrojasdev.tsystemstore.mapper.OrderMapper;
import com.mrojasdev.tsystemstore.mapper.ProductMapper;
import com.mrojasdev.tsystemstore.model.*;
import com.mrojasdev.tsystemstore.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private ProductMapper productMapper;

    @Override
    @Transactional
    public List<OrderDTO> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orderMapper.ordersToOrdersDTO(orders);
    }

    @Override
    @Transactional
    public OrderDTO getOrderById(long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(
                () -> new OrderNotFoundException("order with id "+orderId+" not found")
        );
        return orderMapper.orderToOrderDTO(order);
    }

    @Override
    @Transactional
    public Order placeOrder(Order order) {
        Order savedOrder = orderRepository.save(order);
        return savedOrder;
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

    public void updateOrderPaymentStatus(long orderId, String status) {
        Optional<Order> existingOrder = orderRepository.findById(orderId);
        if(existingOrder.isPresent()){
            Order orderToUpdate = existingOrder.get();
            orderToUpdate.setPaymentStatus(status);
            orderRepository.save(orderToUpdate);
        } else {
            throw new OrderNotFoundException("order doesn't exist in database");
        }
    }

    @Override
    @Transactional
    public List<ProductDTO> listOrderProducts(long orderId){
        Order order = orderRepository.findById(orderId).orElseThrow(
                () -> new OrderNotFoundException("order with id "+orderId+" not found")
        );
        List<ProductDTO> products = new ArrayList<>();
        order.getOrderProducts().stream().forEach(orderProduct -> products.add(
                productMapper.productToProductDTO(
                        orderProduct.getProduct()
                )
        ));
        return products;
    }

    @Override
    @Transactional
    public List<OrderDTO> getOrdersDTObyClient(Client client) {
        List<Order> orders = orderRepository.findByClient(client);
        return orderMapper.ordersToOrdersDTO(orders);
    }

    public List<Order> getOrdersByClient(Client client) {
        return orderRepository.findByClient(client);
    }

}
