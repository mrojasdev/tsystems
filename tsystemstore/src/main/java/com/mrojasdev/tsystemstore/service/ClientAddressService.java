package com.mrojasdev.tsystemstore.service;

import com.mrojasdev.tsystemstore.model.ClientAddress;

import java.util.List;
import java.util.Optional;

public interface ClientAddressService {
    List<ClientAddress> getAllAddresses();
    ClientAddress getAddressById(long addressId);
    void addAddress(ClientAddress address);
    void updateAddress(long addressId, ClientAddress address);
    void deleteAddress(long addressId);
}
