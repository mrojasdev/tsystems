package com.mrojasdev.tsystemstore.service;

import com.mrojasdev.tsystemstore.model.Client;

import java.util.List;
import java.util.Optional;

public interface ClientService {
    List<Client> getAllClients();
    Client getClientById(long clientId);
    void addClient(Client client);
    void updateClient(long clientId, Client client);
    void deleteClient(long clientId);
}
