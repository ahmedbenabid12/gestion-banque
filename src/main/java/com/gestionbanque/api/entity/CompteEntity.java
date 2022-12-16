package com.gestionbanque.api.entity;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;


@Getter
@Setter
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name= "compte")
public class CompteEntity implements Serializable {

    @Id
    private String rib ; //RIB
    private Double solde ;
    private LocalDate dateCreation ;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idClient")
    private ClientEntity client;

    @OneToMany (mappedBy = "compte" , fetch = FetchType.LAZY)
    private List<OperationEntity> operations ;

}
