package com.mrojasdev.tsystemstore.controller;

import com.mrojasdev.tsystemstore.model.Client;
import com.mrojasdev.tsystemstore.model.ClientAddress;
import com.mrojasdev.tsystemstore.model.ClientAddressDTO;
import com.mrojasdev.tsystemstore.service.ClientAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/addresses")
public class ClientAddressController {

    private final ClientAddressService clientAddressService;

    @Autowired
    public ClientAddressController(ClientAddressService clientAddressService){
        this.clientAddressService = clientAddressService;
    }

    @GetMapping
    public List<ClientAddressDTO> getAllAddresses() {
        return clientAddressService.getAllAddresses();
    }

    @GetMapping("/{id}")
    public ClientAddressDTO getAddressById(@PathVariable Long id) {
        return clientAddressService.getAddressById(id);
    }

    @PostMapping
    public String saveAddress(@ModelAttribute("address") ClientAddress clientAddress) {
        clientAddressService.addAddress(clientAddress);
        return "redirect:/addresses";
    }

    @PostMapping("/delete/{id}")
    public String deleteAddress(@PathVariable Long id) {
        clientAddressService.deleteAddress(id);
        return "redirect:/addresses";
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateAddress(@PathVariable Long id, @RequestBody ClientAddress updatedAddress) {
        clientAddressService.updateAddress(id, updatedAddress);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /*
    Saves the address for the current user
     */
    @PostMapping("/current")
    public String saveAddressCurrentUser(@ModelAttribute("address") ClientAddress clientAddress) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Client client = null;
        if(principal instanceof Client){
            client = ((Client) principal);
        }
        clientAddress.setClient(client);

        clientAddressService.addAddress(clientAddress);
        return "redirect:/addresses";
    }


}
