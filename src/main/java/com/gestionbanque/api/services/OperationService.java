package com.gestionbanque.api.services;

import com.gestionbanque.api.entity.CompteEntity;
import com.gestionbanque.api.entity.OperationEntity;
import com.gestionbanque.api.entity.dto.HistriqueOperationDTO;
import com.gestionbanque.api.entity.dto.OperationDTO;
import com.gestionbanque.api.enums.TypeOperation;
import com.gestionbanque.api.exception.BanqueException;
import com.gestionbanque.api.mapper.OperationMapper;
import com.gestionbanque.api.repository.CompteRepository;
import com.gestionbanque.api.repository.OperationRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class OperationService {

    private final OperationRepository operationRepository;
    private final CompteRepository compteRepository;
    private final OperationMapper operationMapper;


    private OperationEntity depot (String rib , double montant) throws BanqueException {
        log.info("versement un montant du {} dans le compte :  "+montant,rib);
        CompteEntity compte = compteRepository.findById(rib).orElseThrow(()->new BanqueException(BanqueException.Type.E001 , "RIB n'existe pas dans la BD")) ;
        var operation = OperationEntity.builder()
                .dateOperation(LocalDate.now())
                .montantOperation(montant)
                .typeOperation(TypeOperation.DEPOT)
                .compte(compte)
                .build();
        compte.setSolde(compte.getSolde()==null ? montant : compte.getSolde()+montant);
        compteRepository.save(compte);
        var result =   operationRepository.save(operation) ;
        log.info("Fin du versement dans le compte : {} "+rib);
        return result ;
    }


    private OperationEntity retrait (String rib , double montant) throws BanqueException {
        log.info("retrait le montant  {} du compte :  "+montant,rib);
        CompteEntity compte = compteRepository.findById(rib).orElseThrow(()->new BanqueException(BanqueException.Type.E001 , "RIB n'existe pas dans la BD")) ;
        if(compte.getSolde()<montant)
            throw new BanqueException(BanqueException.Type.E002 , "vous n'avez pas de solde pour faire ce retrait");
        var operation = OperationEntity.builder()
                .dateOperation(LocalDate.now())
                .montantOperation(montant)
                .typeOperation(TypeOperation.RETRAIT)
                .compte(compte)
                .build();
        compte.setSolde(compte.getSolde()-montant);
        compteRepository.save(compte);
        var result  = operationRepository.save(operation) ;
        log.info("le retrait a été effectué compte : {} "+rib);
        return  result ;
    }

    /**
     * retrait ou depot un montant d'un ompte bancaire
     *
     * @param operationDTO
     * @exception BanqueException
     * @return OperationEntity
     */
    public OperationDTO saveOperation(OperationDTO operationDTO) throws BanqueException {
        var OperationEntity =  operationDTO.getTypeOperation().equals(TypeOperation.DEPOT)
                ? this.depot(operationDTO.getRib(), operationDTO.getMontantOperation())
                : this.retrait(operationDTO.getRib(), operationDTO.getMontantOperation());

        return this.operationMapper.OperationToOperationDTO(OperationEntity);
    }


    /**
     * Récupérer tous les opérations liée a un compte sous forme de pagination
     *
     * @param idCompte rib
     * @param page page en cour
     * @param size nombre d'enregistrements dans une page
     * @exception BanqueException
     * @return List<OperationDTO>
     */
    public HistriqueOperationDTO getOperations(String idCompte , int page , int size) throws BanqueException {
        CompteEntity compte = compteRepository.findById(idCompte).orElseThrow(()->new BanqueException(BanqueException.Type.E001 , "RIB n'existe pas dans la BD")) ;
        if(compte==null)
            throw new BanqueException(BanqueException.Type.E001,"Compte n'existe pas") ;

        var listeOperation = this.operationRepository.findByCompte(compte, PageRequest.of(page,size));
        var listeOperationDto = listeOperation.stream().map(operationMapper::OperationToOperationDTO).collect(Collectors.toList());
        HistriqueOperationDTO histriqueOperationDTO = new HistriqueOperationDTO();
        histriqueOperationDTO.setOperationDTOList(listeOperationDto);
        histriqueOperationDTO.setCurrentPage(page);
        histriqueOperationDTO.setPageSize(size);
            histriqueOperationDTO.setTotalPages(listeOperation.getTotalPages());
        return histriqueOperationDTO ;
    }
}
