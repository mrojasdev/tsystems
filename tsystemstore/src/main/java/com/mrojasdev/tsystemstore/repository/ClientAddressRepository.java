package com.mrojasdev.tsystemstore.repository;

import com.mrojasdev.tsystemstore.model.Client;
import com.mrojasdev.tsystemstore.model.ClientAddress;
import org.springframework.data.repository.CrudRepository;

public interface ClientAddressRepository extends CrudRepository<ClientAddress, Long> {

    ClientAddress findById(long id);

    ClientAddress findByClient(Client client);

}
