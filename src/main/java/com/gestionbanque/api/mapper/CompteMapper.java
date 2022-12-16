package com.gestionbanque.api.mapper;

import com.gestionbanque.api.entity.CompteEntity;
import com.gestionbanque.api.entity.dto.CompteDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;
import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(unmappedTargetPolicy = IGNORE, componentModel = "spring")
@Component
public interface CompteMapper {
    CompteMapper INSTANCE = Mappers.getMapper( CompteMapper.class );

    CompteDTO compteToCompteDTO(CompteEntity compteEntity);

}
