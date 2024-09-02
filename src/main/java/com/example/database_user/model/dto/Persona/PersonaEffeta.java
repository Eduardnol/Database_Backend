package com.example.database_user.model.dto.Persona;

import com.example.database_user.model.dto.discipulado.Effeta;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Informacion extra de las personas que van a Effeta
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PersonaEffeta extends PersonaDTO {

  Boolean servidor;
  Integer numeroDeServicios;
  Effeta retiroComoCaminante;
  List<Effeta> retiroComoServidor;

}
