package com.gestionbanque.api.mapper;

import com.gestionbanque.api.entity.OperationEntity;
import com.gestionbanque.api.entity.dto.OperationDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(unmappedTargetPolicy = IGNORE, componentModel = "spring")
@Component
public interface OperationMapper {
    OperationMapper INSTANCE = Mappers.getMapper( OperationMapper.class );

    OperationDTO OperationToOperationDTO(OperationEntity operationEntity);

}
