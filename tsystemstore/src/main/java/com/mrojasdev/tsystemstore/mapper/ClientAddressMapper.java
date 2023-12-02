package com.mrojasdev.tsystemstore.mapper;

import com.mrojasdev.tsystemstore.model.ClientAddress;
import com.mrojasdev.tsystemstore.model.ClientAddressDTO;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL)
public interface ClientAddressMapper {

    ClientAddressDTO clientAddressToClientAddressDTO(ClientAddress entity);

    ClientAddress clientAddressDTOtoClientAddress(ClientAddressDTO dto);

    List<ClientAddressDTO> clientAdressesToClientAddressesDTO(List<ClientAddress> addresses);

    List<ClientAddress> clientAddressesDTOtoClientAddresses(List<ClientAddressDTO> dtos);

}
