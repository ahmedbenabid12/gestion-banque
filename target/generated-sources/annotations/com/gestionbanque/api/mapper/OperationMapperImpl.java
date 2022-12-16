package com.gestionbanque.api.mapper;

import com.gestionbanque.api.entity.OperationEntity;
import com.gestionbanque.api.entity.dto.OperationDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-12-11T13:34:52+0100",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 11.0.9 (Oracle Corporation)"
)
@Component
public class OperationMapperImpl implements OperationMapper {

    @Override
    public OperationDTO OperationToOperationDTO(OperationEntity operationEntity) {
        if ( operationEntity == null ) {
            return null;
        }

        OperationDTO operationDTO = new OperationDTO();

        operationDTO.setIdOperation( operationEntity.getIdOperation() );
        operationDTO.setDateOperation( operationEntity.getDateOperation() );
        operationDTO.setMontantOperation( operationEntity.getMontantOperation() );
        operationDTO.setTypeOperation( operationEntity.getTypeOperation() );

        return operationDTO;
    }
}
