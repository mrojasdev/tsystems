package com.mrojasdev.tsystemstore.mapper;

import com.mrojasdev.tsystemstore.model.Order;
import com.mrojasdev.tsystemstore.model.OrderDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderDTO orderToOrderDTO(Order entity);

    Order orderDTOtoOrder(OrderDTO dto);

    List<OrderDTO> ordersToOrdersDTO(List<Order> orders);

    List<Order> ordersDTOtoOrders(List<OrderDTO> dtos);

}
