package com.mrojasdev.tsystemstore.service;

import com.mrojasdev.tsystemstore.exception.ClientNotFoundException;
import com.mrojasdev.tsystemstore.exception.DuplicateClientEmailException;
import com.mrojasdev.tsystemstore.mapper.ClientMapper;
import com.mrojasdev.tsystemstore.mapper.OrderMapper;
import com.mrojasdev.tsystemstore.model.*;
import com.mrojasdev.tsystemstore.repository.ClientRepository;
import com.mrojasdev.tsystemstore.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ClientMapper clientMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    @Transactional
    public List<ClientDTO> getAllClients() {
        List<Client> clients = clientRepository.findAll();
        return clientMapper.clientsToClientsDTO(clients);
    }

    @Override
    @Transactional
    public ClientDTO getClientById(long clientId) {
        Client client = clientRepository.findById(clientId).orElseThrow(
                () -> new ClientNotFoundException("Client with id "+clientId+" not found")
        );
        return clientMapper.clientToClientDTO(client);
    }

    @Override
    @Transactional
    public void addClient(Client client) {
        try{
            client.setRole("ROLE_USER");
            client.setPassword(passwordEncoder.encode(client.getPassword()));
            clientRepository.save(client);
        }catch(DataIntegrityViolationException ex){
            throw new DuplicateClientEmailException("Email already exists in database");
        }

    }

    @Override
    @Transactional
    public void updateClient(long clientId, Client updatedClient) {
        Optional<Client> existingClient = clientRepository.findById(clientId);
        if(existingClient.isPresent()){
            Client clientToUpdate = existingClient.get();
            clientToUpdate.setBirthdate(updatedClient.getBirthdate());
            clientToUpdate.setFirstname(updatedClient.getFirstname());
            clientToUpdate.setSurname(updatedClient.getSurname());
            clientToUpdate.setPassword(updatedClient.getPassword());
            clientRepository.save(clientToUpdate);
        } else {
            throw new ClientNotFoundException("client doesn't exist in database");
        }
    }

    @Override
    @Transactional
    public void deleteClient(long clientId) {
        clientRepository.deleteById(clientId);
    }

    @Override
    @Transactional
    public List<OrderDTO> listClientOrders(long clientId){
        List<Order> orders = orderRepository.findAll().stream()
                .filter(order -> order.getClient().getId().equals(clientId))
                .toList();
        return orderMapper.ordersToOrdersDTO(orders);
    }

    @Override
    @Transactional
    public List<TopClientDTO> getTopTenClientsMostOrders() {
        List<Order> orderList = orderRepository.findAll();

        Map<Client, Long> orderCountByClient = orderList.stream()
                .collect(Collectors.groupingBy(Order::getClient, Collectors.counting()));

        return orderCountByClient.entrySet().stream()
                .sorted((entry1, entry2) -> Long.compare(entry2.getValue(), entry1.getValue()))
                .limit(10)
                .map(entry -> new TopClientDTO(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }
}
