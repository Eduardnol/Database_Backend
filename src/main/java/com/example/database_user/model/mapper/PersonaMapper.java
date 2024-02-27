package com.example.database_user.model.mapper;

import com.example.database_user.model.dto.Persona.PersonaDTO;
import com.example.database_user.repositories.entity.PersonaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {PersonaSacramentsMapper.class})
public interface PersonaMapper {

  @Mapping(target = "fileStorage", ignore = true)
  @Mapping(target = "personaNinos", ignore = true)
  @Mapping(target = "homeAddress", ignore = true)
  @Mapping(target = "personGroups", ignore = true)
  @Mapping(target = "createdOn", ignore = true)
  PersonaDTO toDTO(PersonaEntity persona);

  @Mapping(target = "fileStorage", ignore = true)
  @Mapping(target = "personaNinos", ignore = true)
  @Mapping(target = "homeAddress", ignore = true)
  @Mapping(target = "personGroups", ignore = true)
  @Mapping(target = "createdOn", ignore = true)
  PersonaEntity toEntity(PersonaDTO persona);

}