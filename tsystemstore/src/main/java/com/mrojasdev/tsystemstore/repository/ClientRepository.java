package com.mrojasdev.tsystemstore.repository;

import com.mrojasdev.tsystemstore.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.awt.print.Pageable;
import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {

    Client findByEmail(String email);
}
