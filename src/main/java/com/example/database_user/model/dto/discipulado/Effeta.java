package com.example.database_user.model.dto.discipulado;

import com.example.database_user.model.dto.Persona.PersonaEffeta;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.LocalDate;
import java.util.List;
import lombok.Data;

/**
 * Indica la informacion relativa a un retiro de Effeta
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Effeta {

  /**
   * Donde ha sido el retiro
   */
  String ubicacion;
  /**
   * Fecha de inicio del retiro
   */
  LocalDate fecha;
  /**
   * Caminantes en el retiro
   */
  List<PersonaEffeta> caminantes;
  /**
   * Servidores del retiro
   */
  List<PersonaEffeta> servidores;
  /**
   * Algun que otro comentario del retiro
   */
  String comentarios;
}
