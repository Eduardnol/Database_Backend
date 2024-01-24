package com.example.database_user.domain.model.mapper;

import com.example.database_user.controllers.dto.Persona.PersonaDTO;
import com.example.database_user.repositories.entity.PersonaEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface PersonaMapper {

  PersonaDTO toDTO(PersonaEntity persona);

  PersonaEntity toEntity(PersonaDTO persona);

}
