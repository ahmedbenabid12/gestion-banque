package com.gestionbanque.api.repository;

import com.gestionbanque.api.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity,Long> {

    List<ClientEntity> findAllByNomClientContains(String nom);
    Optional<ClientEntity>  findByEmail(String mail);
}
