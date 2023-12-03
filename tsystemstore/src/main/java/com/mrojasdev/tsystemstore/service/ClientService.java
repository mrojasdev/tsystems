package com.mrojasdev.tsystemstore.service;

import com.mrojasdev.tsystemstore.model.Client;
import com.mrojasdev.tsystemstore.model.ClientDTO;
import com.mrojasdev.tsystemstore.model.OrderDTO;

import java.util.List;
import java.util.Optional;

public interface ClientService {
    List<ClientDTO> getAllClients();
    ClientDTO getClientById(long clientId);
    void addClient(Client client);
    void updateClient(long clientId, Client client);
    void deleteClient(long clientId);
    List<OrderDTO> listClientOrders(long clientId);
}
