package com.mrojasdev.tsystemstore.service;

import com.mrojasdev.tsystemstore.exception.AddressNotFoundException;
import com.mrojasdev.tsystemstore.mapper.ClientAddressMapper;
import com.mrojasdev.tsystemstore.model.ClientAddress;
import com.mrojasdev.tsystemstore.model.ClientAddressDTO;
import com.mrojasdev.tsystemstore.repository.ClientAddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientAddressServiceImpl implements ClientAddressService {

    @Autowired
    private ClientAddressRepository clientAddressRepository;

    @Autowired
    private ClientAddressMapper clientAddressMapper;

    @Override
    @Transactional
    public List<ClientAddressDTO> getAllAddresses() {
        List<ClientAddress> addresses = clientAddressRepository.findAll();
        return clientAddressMapper.clientAdressesToClientAddressesDTO(addresses);
    }

    @Override
    @Transactional
    public ClientAddressDTO getAddressById(long addressId) {
        ClientAddress address = clientAddressRepository.findById(addressId).orElseThrow(
                () -> new AddressNotFoundException("address with id "+addressId+" not found")
        );
        return clientAddressMapper.clientAddressToClientAddressDTO(address);
    }

    @Override
    @Transactional
    public void addAddress(ClientAddress address) {
        clientAddressRepository.save(address);
    }

    @Override
    @Transactional
    public void updateAddress(long addressId, ClientAddress updatedAddress) {
        Optional<ClientAddress> existingAddress = clientAddressRepository.findById(addressId);
        if(existingAddress.isPresent()){
            ClientAddress addressToUpdate = existingAddress.get();
            addressToUpdate.setApartment(updatedAddress.getApartment());
            addressToUpdate.setCity(updatedAddress.getCity());
            addressToUpdate.setHome(updatedAddress.getHome());
            addressToUpdate.setCountry(updatedAddress.getCountry());
            addressToUpdate.setPostalCode(updatedAddress.getPostalCode());
            addressToUpdate.setStreet(updatedAddress.getStreet());
            clientAddressRepository.save(addressToUpdate);
        } else {
            throw new AddressNotFoundException("address doesn't exist in database");
        }
    }

    @Override
    @Transactional
    public void deleteAddress(long addressId) {
        clientAddressRepository.deleteById(addressId);
    }
}
