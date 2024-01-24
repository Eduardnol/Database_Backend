package com.example.database_user.repositories.entity;

import com.example.database_user.controllers.dto.Custom;
import com.example.database_user.controllers.dto.FileStorage;
import com.example.database_user.controllers.dto.PersonGroups;
import com.example.database_user.controllers.dto.Persona.PersonaNinos;
import com.example.database_user.controllers.dto.Persona.PersonaSacraments;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@Document
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PersonaEntity {

  @Id
  private String id;
  @TextIndexed(weight = 3)
  @NotNull(message = "The name is required.")
  private String nombre;

  @TextIndexed(weight = 2)
  @NotNull(message = "The surname is required.")
  private String apellido;

  @TextIndexed(weight = 2)
  @NotNull(message = "The surname2 is required.")
  private String apellido2;

  @TextIndexed
  @Email(message = "The email address is invalid.", flags = {Pattern.Flag.CASE_INSENSITIVE})
  private String email;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  @JsonFormat(pattern = "yyyy-MM-dd")
  @Past(message = "The date of birth must be in the past.")
  private LocalDate birthday;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  @JsonFormat(pattern = "yyyy-MM-dd")
  @PastOrPresent(message = "The saint must be past or present")
  private LocalDate saint;

  private String dni;
  private List<Custom> extras;
  private List<PersonaSacraments> sacraments;
  private List<FileStorage> fileStorage;
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime createdOn;

  private List<PersonGroups> personGroups;

  //Home address
  private String homeAddress;

  //Optional fields
  private PersonaNinos personaNinos = null;
}
