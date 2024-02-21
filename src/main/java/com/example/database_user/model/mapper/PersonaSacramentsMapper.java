package com.example.database_user.model.mapper;

import com.example.database_user.model.dto.Persona.PersonaSacramentsDTO;
import com.example.database_user.repositories.entity.PersonaSacramentsEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface PersonaSacramentsMapper {

  PersonaSacramentsDTO toDTO(PersonaSacramentsEntity personaSacramentsEntity);

  PersonaSacramentsEntity toEntity(PersonaSacramentsDTO personaSacramentsDTO);


}
