package com.example.database_user.controllers.dto.Persona;

import com.example.database_user.controllers.dto.Custom;
import com.example.database_user.controllers.dto.FileStorage;
import com.example.database_user.controllers.dto.PersonGroups;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Data;

//TODO: verificar todos los campos que nos introducen
@Builder
@Data
public class PersonaDTO {
  private String id;
  private String nombre;
  private String apellido;
  private String apellido2;
  private String email;
  private LocalDate birthday;
  private LocalDate saint;
  private String dni;
  private List<Custom> extras;
  private List<PersonaSacraments> sacraments;
  private List<FileStorage> fileStorage;
  private LocalDateTime createdOn;
  private List<PersonGroups> personGroups;
  private String homeAddress;
  private PersonaNinos personaNinos = null;


}
