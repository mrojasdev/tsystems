package com.mrojasdev.tsystemstore.mapper;

import com.mrojasdev.tsystemstore.model.Order;
import com.mrojasdev.tsystemstore.model.OrderDTO;
import org.mapstruct.Mapper;

@Mapper
public interface OrderMapper {

    OrderDTO orderToOrderDTO(Order entity);

    Order orderDTOtoOrder(OrderDTO dto);

}
