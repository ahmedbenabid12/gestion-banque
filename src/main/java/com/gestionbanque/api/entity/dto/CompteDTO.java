package com.gestionbanque.api.entity.dto;


import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class CompteDTO {
    private String rib ;
    private Double solde ;
    private LocalDate dateCreation ;
}
