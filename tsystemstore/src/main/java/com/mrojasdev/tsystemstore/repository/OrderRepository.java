package com.mrojasdev.tsystemstore.repository;

import com.mrojasdev.tsystemstore.model.Client;
import com.mrojasdev.tsystemstore.model.Order;
import com.mrojasdev.tsystemstore.model.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Long> {

    Order findById(long id);

    List<Order> findByClient(Client client);

    List<Order> findByProduct(Product product);

}
