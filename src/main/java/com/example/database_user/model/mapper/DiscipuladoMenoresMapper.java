package com.example.database_user.model.mapper;

import com.example.database_user.model.dto.discipulado.DiscipuladoMenoresDTO;
import com.example.database_user.repositories.entity.DiscipuladoMenoresEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DiscipuladoMenoresMapper {

  DiscipuladoMenoresDTO toDTO(DiscipuladoMenoresEntity persona);

  DiscipuladoMenoresEntity toEntity(DiscipuladoMenoresDTO persona);

}
