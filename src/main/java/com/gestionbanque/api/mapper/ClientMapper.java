package com.gestionbanque.api.mapper;

import ch.qos.logback.core.net.server.Client;
import com.gestionbanque.api.entity.ClientEntity;
import com.gestionbanque.api.entity.dto.ClientDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(unmappedTargetPolicy = IGNORE, componentModel = "spring")
@Component
public interface ClientMapper {
    ClientMapper INSTANCE = Mappers.getMapper( ClientMapper.class );



    ClientDTO clientToClientDTO(ClientEntity clientEntity);
    ClientEntity clientDTOToClient(ClientDTO clientDTO);

    List<ClientEntity> listeClientDTOToListeClient(List<ClientDTO> clientsDTO);
    List<ClientDTO> listeClientToListeClientDTO(List<ClientEntity> clients);


}
