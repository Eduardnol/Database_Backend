package com.example.database_user.domain.model.mapper;

import com.example.database_user.controllers.dto.UserDTO;
import com.example.database_user.repositories.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface UserMapper {

  UserDTO toDTO(UserEntity userEntity);

  UserEntity toEntity(UserDTO userDTO);

}
