package com.gestionbanque.api.mapper;

import com.gestionbanque.api.entity.CompteEntity;
import com.gestionbanque.api.entity.dto.CompteDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-12-11T13:34:52+0100",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 11.0.9 (Oracle Corporation)"
)
@Component
public class CompteMapperImpl implements CompteMapper {

    @Override
    public CompteDTO compteToCompteDTO(CompteEntity compteEntity) {
        if ( compteEntity == null ) {
            return null;
        }

        CompteDTO compteDTO = new CompteDTO();

        compteDTO.setRib( compteEntity.getRib() );
        compteDTO.setSolde( compteEntity.getSolde() );
        compteDTO.setDateCreation( compteEntity.getDateCreation() );

        return compteDTO;
    }
}
