package com.gestionbanque.api.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
public class ClientDTO {
    private Long idClient ;
    private String nomClient ;
    private String prenomClient ;
    private String email ;
}
