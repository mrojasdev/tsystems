package com.mrojasdev.tsystemstore.service;

import com.mrojasdev.tsystemstore.model.Client;
import com.mrojasdev.tsystemstore.model.ClientDTO;
import com.mrojasdev.tsystemstore.model.OrderDTO;
import com.mrojasdev.tsystemstore.model.TopClientDTO;

import java.util.List;

public interface ClientService {
    List<ClientDTO> getAllClients();
    ClientDTO getClientById(long clientId);
    void addClient(Client client);
    void updateClient(long clientId, Client client);
    void deleteClient(long clientId);
    List<OrderDTO> listClientOrders(long clientId);
    List<TopClientDTO> getTopTenClientsMostOrders();
}
