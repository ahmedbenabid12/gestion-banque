package com.gestionbanque.api.services;

import com.gestionbanque.api.entity.ClientEntity;
import com.gestionbanque.api.entity.dto.CompteDTO;
import com.gestionbanque.api.exception.BanqueException;
import com.gestionbanque.api.mapper.CompteMapper;
import com.gestionbanque.api.repository.ClientRepository;
import com.gestionbanque.api.repository.CompteRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class CompteService {

    private final CompteRepository compteRepository ;
    private final ClientRepository clientRepository ;
    private final CompteMapper compteMapper ;

    /**
     * Récupérer tous les comptes d'un client
     *
     * @param idClient l'identifiant du client
     * @return List<CompteDTO>
     */
    public List<CompteDTO> getComptes(Long idClient) throws BanqueException {
        log.info("Récupération des comptes d'un client");
        ClientEntity clientEntity = clientRepository.findById(idClient).orElseThrow(()->new BanqueException(BanqueException.Type.E001 , "Client n'existe pas dans la BD") ) ;
        var listeCompte =   compteRepository.findAllByClient(clientEntity) ;
        return listeCompte.stream()
                .map(compteMapper::compteToCompteDTO)
                .collect(Collectors.toList());
    }


}
