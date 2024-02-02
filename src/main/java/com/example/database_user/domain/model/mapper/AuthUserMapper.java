package com.example.database_user.domain.model.mapper;

import com.example.database_user.controllers.dto.AuthUserDTO;
import com.example.database_user.repositories.entity.AuthUserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface AuthUserMapper {

  AuthUserDTO toDTO(AuthUserEntity authUserEntity);

  AuthUserEntity toEntity(AuthUserDTO authUserDTO);

}
