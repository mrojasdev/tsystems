package com.mrojasdev.tsystemstore.mapper;

import com.mrojasdev.tsystemstore.model.Client;
import com.mrojasdev.tsystemstore.model.ClientDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    ClientDTO clientToClientDTO(Client entity);

    Client clientDTOtoClient(ClientDTO dto);

    List<ClientDTO> clientsToClientsDTO(List<Client> clients);

    List<Client> clientsDTOtoClients(List<ClientDTO> dtos);

}
