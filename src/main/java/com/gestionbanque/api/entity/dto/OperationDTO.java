package com.gestionbanque.api.entity.dto;

import com.gestionbanque.api.enums.TypeOperation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class OperationDTO {

    private Long idOperation ;
    private LocalDate dateOperation ;
    private Double montantOperation ;
    private TypeOperation typeOperation ;
    private String rib ;
}
