package com.mrojasdev.tsystemstore.service;

import com.mrojasdev.tsystemstore.exception.ClientNotFoundException;
import com.mrojasdev.tsystemstore.exception.DuplicateClientEmailException;
import com.mrojasdev.tsystemstore.mapper.ClientMapper;
import com.mrojasdev.tsystemstore.model.Client;
import com.mrojasdev.tsystemstore.model.ClientDTO;
import com.mrojasdev.tsystemstore.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ClientMapper clientMapper;


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
}
