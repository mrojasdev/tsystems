package com.mrojasdev.tsystemstore.controller;

import com.mrojasdev.tsystemstore.model.ClientAddress;
import com.mrojasdev.tsystemstore.service.ClientAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/addresses")
public class ClientAddressController {

    private final ClientAddressService clientAddressService;

    @Autowired
    public ClientAddressController(ClientAddressService clientAddressService){
        this.clientAddressService = clientAddressService;
    }

    @GetMapping
    public List<ClientAddress> getAllAddresses() {
        return clientAddressService.getAllAddresses();
    }

    @GetMapping("/{id}")
    public ClientAddress getAddressById(@PathVariable Long id) {
        return clientAddressService.getAddressById(id);
    }

    @PostMapping
    public ResponseEntity<ClientAddress> saveAddress(@RequestBody ClientAddress clientAddress) {
        clientAddressService.addAddress(clientAddress);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Long id) {
        clientAddressService.deleteAddress(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateAddress(@PathVariable Long id, @RequestBody ClientAddress updatedAddress) {
        clientAddressService.updateAddress(id, updatedAddress);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
