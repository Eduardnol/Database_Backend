package com.example.database_user.model.dto.Persona;

import com.example.database_user.model.dto.Custom.CustomTagDTO;
import com.example.database_user.model.dto.FileStorage;
import com.example.database_user.model.dto.PersonGroups;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//TODO: verificar todos los campos que nos introducen
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonaDTO {

  private String id;
  private String nombre;
  private String apellido;
  private String apellido2;
  private String email;
  private LocalDate birthday;
  private LocalDate saint;
  private String dni;
  private List<CustomTagDTO> extras;
  private List<PersonaSacramentsDTO> sacraments;
  private List<FileStorage> fileStorage;
  private LocalDateTime createdOn;
  private List<PersonGroups> personGroups;
  private String homeAddress;
  private PersonaNinos personaNinos;
}
