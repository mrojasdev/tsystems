package com.mrojasdev.tsystemstore.repository;

import com.mrojasdev.tsystemstore.model.Client;
import com.mrojasdev.tsystemstore.model.Order;
import com.mrojasdev.tsystemstore.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.awt.print.Pageable;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByClient(Client client);


    //List<Order> findByProduct(Product product);

}
