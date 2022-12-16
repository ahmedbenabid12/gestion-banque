package com.gestionbanque.api;

import com.gestionbanque.api.entity.ClientEntity;
import com.gestionbanque.api.entity.CompteEntity;
import com.gestionbanque.api.entity.OperationEntity;
import com.gestionbanque.api.enums.TypeOperation;
import com.gestionbanque.api.repository.ClientRepository;
import com.gestionbanque.api.repository.CompteRepository;
import com.gestionbanque.api.repository.OperationRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.time.LocalDate;
import java.util.stream.Stream;

@SpringBootApplication
public class GestionBanqueApplication {

    public static void main(String[] args) {
        SpringApplication.run(GestionBanqueApplication.class, args);
    }


    @Bean
    CommandLineRunner start(ClientRepository clientRepository ,
                            CompteRepository compteRepository ,
                            OperationRepository operationRepository){
      return args -> {
          Stream.of("Client 1", "Client 2", "Client 3").forEach(val->{
              ClientEntity clientEntity = new ClientEntity() ;
              clientEntity.setNomClient(val);
              clientEntity.setEmail(val+"@test.com");
              clientRepository.save(clientEntity);
          });


          var listeClient = clientRepository.findAll();
          listeClient.forEach(client-> {
              CompteEntity compteEntity = new CompteEntity() ;
              var rib = (int) (Math.random() *  Math.pow(10,5)); //on va supposer que le RIB est composé de 5 chiffres
              compteEntity.setRib(Integer.toString(rib));
              compteEntity.setDateCreation(LocalDate.now());
              compteEntity.setClient(client);
              compteEntity.setSolde(0.0);
              compteRepository.save(compteEntity);

              CompteEntity compteEntity2 = new CompteEntity() ;
              var rib2 = (int) (Math.random() *  Math.pow(10,5))  ; //on va supposer que le RIB est composé de 5 chiffres
              compteEntity2.setRib(Integer.toString(rib2));
              compteEntity2.setDateCreation(LocalDate.now());
              compteEntity2.setClient(client);
              compteEntity2.setSolde(0.0);
              compteRepository.save(compteEntity2);

          });

          var listeCompte = compteRepository.findAll();
          listeCompte.forEach(compte -> {
              for (int i = 0; i <10 ; i++) {
                  var testTypeOp = Math.random()>0.5 ;
                  var montantOp = Math.random()*150 ;
                  OperationEntity operation = new OperationEntity();
                  operation.setDateOperation(LocalDate.now());
                  operation.setTypeOperation(testTypeOp ? TypeOperation.DEPOT : TypeOperation.RETRAIT);
                  operation.setMontantOperation(montantOp);
                  operation.setCompte(compte);
                  compte.setSolde(testTypeOp ? compte.getSolde()+montantOp : compte.getSolde()-montantOp);
                  compteRepository.save(compte);
                  operationRepository.save(operation);
              }
          });
      };
    }
}
