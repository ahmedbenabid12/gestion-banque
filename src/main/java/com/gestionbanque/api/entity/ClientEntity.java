package com.gestionbanque.api.entity;


import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Builder
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name= "client")
public class ClientEntity implements Serializable {


    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idClient ;
    private String nomClient ;
    private String prenomClient ;
    private String email ;

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    private List<CompteEntity> comptesClient;
}
