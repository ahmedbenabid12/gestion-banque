package com.gestionbanque.api.repository;

import com.gestionbanque.api.entity.ClientEntity;
import com.gestionbanque.api.entity.CompteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompteRepository  extends JpaRepository<CompteEntity,String> {

    List<CompteEntity> findAllByClient(ClientEntity clientEntity);
}
