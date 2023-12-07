package com.mrojasdev.tsystemstore.security;

import com.mrojasdev.tsystemstore.model.Client;
import com.mrojasdev.tsystemstore.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class ClientDetailsService implements UserDetailsService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Client client = clientRepository.findByEmail(email);
        if (client == null ) {
            throw new UsernameNotFoundException("User not found: "+email);
        }
        return client;
    }

}
