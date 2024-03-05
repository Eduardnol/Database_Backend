package com.example.database_user.model.mapper;

import com.example.database_user.model.dto.custom.CustomFieldsDTO;
import com.example.database_user.model.dto.custom.CustomTagDTO;
import com.example.database_user.repositories.entity.CustomFieldsEntity;
import com.example.database_user.repositories.entity.CustomTagEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {CustomTagDTO.class})
public interface CustomFieldsMapper {

  CustomFieldsDTO toDTO(CustomFieldsEntity customFieldsEntity);

  CustomFieldsEntity toEntity(CustomFieldsDTO customFieldsDTO);

}
