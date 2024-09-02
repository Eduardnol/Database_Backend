package com.example.database_user.repositories.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "persona")
@TypeAlias("personaEffeta")
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PersonaEffetaEntity extends PersonaEntity {

  private Boolean servidor;
  private Integer numeroDeServicios;
  private EffetaEntity retiroComoCaminante;
  private List<EffetaEntity> retiroComoServidor;


}
