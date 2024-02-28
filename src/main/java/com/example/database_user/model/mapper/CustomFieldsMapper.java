package com.example.database_user.model.mapper;

import com.example.database_user.model.dto.Custom.CustomFieldsDTO;
import com.example.database_user.model.dto.Custom.CustomTagDTO;
import com.example.database_user.repositories.entity.CustomFieldsEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {CustomTagDTO.class})
public interface CustomFieldsMapper {

  CustomFieldsDTO toDTO(CustomFieldsEntity customFieldsEntity);

  CustomFieldsEntity toEntity(CustomFieldsDTO customFieldsDTO);

}
