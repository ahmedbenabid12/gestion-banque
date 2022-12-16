package com.gestionbanque.api.entity;

import com.gestionbanque.api.enums.TypeOperation;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name= "operation" )
public class OperationEntity implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOperation ;
    private LocalDate dateOperation ;
    private Double montantOperation ;
    @Enumerated(EnumType.STRING)
    private TypeOperation typeOperation ;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idCompte")
    private CompteEntity compte;
}
