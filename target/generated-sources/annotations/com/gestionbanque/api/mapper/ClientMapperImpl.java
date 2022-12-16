package com.gestionbanque.api.mapper;

import com.gestionbanque.api.entity.ClientEntity;
import com.gestionbanque.api.entity.dto.ClientDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-12-11T13:34:52+0100",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 11.0.9 (Oracle Corporation)"
)
@Component
public class ClientMapperImpl implements ClientMapper {

    @Override
    public ClientDTO clientToClientDTO(ClientEntity clientEntity) {
        if ( clientEntity == null ) {
            return null;
        }

        ClientDTO clientDTO = new ClientDTO();

        clientDTO.setIdClient( clientEntity.getIdClient() );
        clientDTO.setNomClient( clientEntity.getNomClient() );
        clientDTO.setPrenomClient( clientEntity.getPrenomClient() );
        clientDTO.setEmail( clientEntity.getEmail() );

        return clientDTO;
    }

    @Override
    public ClientEntity clientDTOToClient(ClientDTO clientDTO) {
        if ( clientDTO == null ) {
            return null;
        }

        ClientEntity.ClientEntityBuilder clientEntity = ClientEntity.builder();

        clientEntity.idClient( clientDTO.getIdClient() );
        clientEntity.nomClient( clientDTO.getNomClient() );
        clientEntity.prenomClient( clientDTO.getPrenomClient() );
        clientEntity.email( clientDTO.getEmail() );

        return clientEntity.build();
    }

    @Override
    public List<ClientEntity> listeClientDTOToListeClient(List<ClientDTO> clientsDTO) {
        if ( clientsDTO == null ) {
            return null;
        }

        List<ClientEntity> list = new ArrayList<ClientEntity>( clientsDTO.size() );
        for ( ClientDTO clientDTO : clientsDTO ) {
            list.add( clientDTOToClient( clientDTO ) );
        }

        return list;
    }

    @Override
    public List<ClientDTO> listeClientToListeClientDTO(List<ClientEntity> clients) {
        if ( clients == null ) {
            return null;
        }

        List<ClientDTO> list = new ArrayList<ClientDTO>( clients.size() );
        for ( ClientEntity clientEntity : clients ) {
            list.add( clientToClientDTO( clientEntity ) );
        }

        return list;
    }
}
