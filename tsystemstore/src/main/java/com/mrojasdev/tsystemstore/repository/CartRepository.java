package com.mrojasdev.tsystemstore.repository;

import com.mrojasdev.tsystemstore.model.Cart;
import com.mrojasdev.tsystemstore.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {

    List<Cart> findByClient(Client client);

}
