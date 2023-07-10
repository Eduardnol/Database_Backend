package com.example.database_user.dtos.Subgrupo;

import com.example.database_user.dtos.Persona.SimplePersona;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Subgrupo {

  private String id;
  private String titulo;
  private List<SimplePersona> responsables;
  private List<SimplePersona> integrantes;
  private String descripcion;
}
