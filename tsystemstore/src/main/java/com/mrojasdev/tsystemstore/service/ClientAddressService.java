package com.mrojasdev.tsystemstore.service;

import com.mrojasdev.tsystemstore.model.Client;
import com.mrojasdev.tsystemstore.model.ClientAddress;
import com.mrojasdev.tsystemstore.model.ClientAddressDTO;

import java.util.List;
import java.util.Optional;

public interface ClientAddressService {
    List<ClientAddressDTO> getAllAddresses();
    ClientAddressDTO getAddressById(long addressId);
    void addAddress(ClientAddress address);
    void updateAddress(long addressId, ClientAddress address);
    void deleteAddress(long addressId);
    List<ClientAddressDTO> getAddressesByClient(Client client);
}
