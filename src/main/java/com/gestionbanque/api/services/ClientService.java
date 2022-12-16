package com.gestionbanque.api.services;


import com.gestionbanque.api.entity.ClientEntity;
import com.gestionbanque.api.entity.dto.ClientDTO;
import com.gestionbanque.api.exception.BanqueException;
import com.gestionbanque.api.mapper.ClientMapper;
import com.gestionbanque.api.repository.ClientRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class ClientService {
    private final ClientRepository clientRepository ;
    private final ClientMapper clientMapper ;

    /**
     * Récupérer la liste des clients
     *
     * @return List<ClientDTO>
     */
    public List<ClientDTO> getCLients() {
        log.info("Récupération la liste des clients");
        var  listeClients = clientRepository.findAll();
        return clientMapper.listeClientToListeClientDTO(listeClients);

//        return listeClients.stream()
//                .map(clientMapper::clientToClientDTO)
//                .collect(Collectors.toList()) ;

    }

    /**
     *  Chercher des clients
     * @param nom le nom à chercher
     * @return List<ClientDTO>
     */
    public List<ClientDTO> getSearchClients(String nom) {
        log.info("Récupération la liste des clients");
        var  listeClients = clientRepository.findAllByNomClientContains(nom);
        return clientMapper.listeClientToListeClientDTO(listeClients);
    }

    /**
     *  Ajouter un nouveau client
     * @param clientDTO le nom à ajouter
     * @exception BanqueException
     * @return ClientDTO
     */
    public ClientEntity saveClient(ClientDTO clientDTO) throws BanqueException {
        Optional<ClientEntity> client = clientRepository.findByEmail(clientDTO.getEmail());
        if(client.isPresent())
            throw new BanqueException(BanqueException.Type.E003,"email existe deja") ;

        var result = clientRepository.save(clientMapper.clientDTOToClient(clientDTO));
        log.info("Ajout d'un client OK");
        return result;
    }
}
