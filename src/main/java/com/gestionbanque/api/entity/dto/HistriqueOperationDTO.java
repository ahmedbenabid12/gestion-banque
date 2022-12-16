package com.gestionbanque.api.entity.dto;

import com.gestionbanque.api.enums.TypeOperation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class HistriqueOperationDTO {

    private List<OperationDTO> operationDTOList;
    private int currentPage ;
    private int totalPages ;
    private int pageSize;

}
