package com.gestionbanque.api.repository;

import com.gestionbanque.api.entity.CompteEntity;
import com.gestionbanque.api.entity.OperationEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationRepository  extends JpaRepository<OperationEntity,Long> {

    Page<OperationEntity> findByCompte(CompteEntity compteEntity , Pageable pageable);
}
