package com.mrojasdev.tsystemstore.controller;

import com.mrojasdev.tsystemstore.model.Client;
import com.mrojasdev.tsystemstore.model.ClientDTO;
import com.mrojasdev.tsystemstore.model.OrderDTO;
import com.mrojasdev.tsystemstore.model.Product;
import com.mrojasdev.tsystemstore.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/clients")
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService){
        this.clientService = clientService;
    }

    @GetMapping
    public List<ClientDTO> getAllClients() {
        return clientService.getAllClients();
    }

    @GetMapping("/{id}")
    public ClientDTO getClientById(@PathVariable Long id) {
        return clientService.getClientById(id);
    }

    @PostMapping
    public String saveClient(@ModelAttribute("client") Client client) {
        clientService.addClient(client);
        return "redirect:/";
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/update/{id}")
    public String updateClient(@PathVariable Long id, @ModelAttribute("client") Client updatedClient) {
        clientService.updateClient(id, updatedClient);
        return "redirect:/profile";
    }

    /*
    Lists all the orders made by a given Client
     */
    @GetMapping("/{id}/orders")
    public List<OrderDTO> listClientOrders(@PathVariable Long id) {
        return clientService.listClientOrders(id);
    }

}
