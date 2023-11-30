package com.mrojasdev.tsystemstore.repository;

import com.mrojasdev.tsystemstore.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {


    Client findByEmail(String email);
}
