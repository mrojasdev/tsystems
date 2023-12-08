package com.mrojasdev.tsystemstore.repository;

import com.mrojasdev.tsystemstore.model.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {


}
