package com.mrojasdev.tsystemstore.mapper;

import com.mrojasdev.tsystemstore.model.Client;
import com.mrojasdev.tsystemstore.model.ClientDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ClientMapper {

    ClientDTO clientToClientDTO(Client entity);

    Client clientDTOtoClient(ClientDTO dto);

}
