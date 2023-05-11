package com.example.database_user.dtos.Persona;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SimplePersona {

  private String id;
  private String nombre;
  private String apellido;
}
