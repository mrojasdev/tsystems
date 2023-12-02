package com.mrojasdev.tsystemstore.mapper;

import com.mrojasdev.tsystemstore.model.ClientAddress;
import com.mrojasdev.tsystemstore.model.ClientAddressDTO;
import org.mapstruct.Mapper;

@Mapper
public interface ClientAddressMapper {

    ClientAddressDTO clientAddressToClientAddressDTO(ClientAddress entity);

    ClientAddress clientAddressDTOtoClientAddress(ClientAddressDTO dto);

}
