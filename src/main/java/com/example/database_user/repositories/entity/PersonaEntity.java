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
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@Document(collection = "persona")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PersonaEntity {

    @Id
    @Field("_id")
    private String id;

    @NotNull(message = "The name is required.")
    @Field("nombre")
    private String nombre;

    @NotNull(message = "The surname is required.")
    @Field("apellido")
    private String apellido;

    @NotNull(message = "The surname2 is required.")
    @Field("apellido2")
    private String apellido2;

    @TextIndexed
    @Email(message = "The email address is invalid.", flags = {Pattern.Flag.CASE_INSENSITIVE})
    @Field("email")
    private String email;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Past(message = "The date of birth must be in the past.")
    @Field("birthday")
    private LocalDate birthday;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    @PastOrPresent(message = "The saint must be past or present")
    @Field("saint")
    private LocalDate saint;

    @Field("dni")
    private String dni;

    @Field("extras")
    private List<Custom> extras;

    @Field("sacraments")
    private List<PersonaSacraments> sacraments;

    @Field("fileStorage")
    private List<FileStorage> fileStorage;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Field("createdOn")
    private LocalDateTime createdOn;

    @Field("personGroups")
    private List<PersonGroups> personGroups;

    //Home address
    @Field("homeAddress")
    private String homeAddress;

    //Optional fields
    @Field("personaNinos")
    private PersonaNinos personaNinos;
}
