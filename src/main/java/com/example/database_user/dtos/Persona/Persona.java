package com.example.database_user.dtos.Persona;

import com.example.database_user.dtos.Custom;
import com.example.database_user.dtos.FileStorage;
import com.example.database_user.dtos.PersonGroups;
import com.example.database_user.dtos.Sacraments;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

//TODO: verificar todos los campos que nos introducen
@Data
@Document
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Persona {

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
    private ArrayList<Custom> extras;
    private Sacraments sacraments;
    private ArrayList<FileStorage> fileStorage;
    @DateTimeFormat(iso = ISO.DATE_TIME)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdOn;

    private ArrayList<PersonGroups> personGroups;

    //Optional fields
    private PersonaNinos personaNinos = null;


    /*public Persona(String nombre, String apellido, String apellido2, String email, LocalDate birthday, LocalDate
    saint,
                   String dni, ArrayList<Custom> extras, Sacraments sacraments, ArrayList<FileStorage> fileStorage,
                   LocalDateTime createdOn) {

        this.nombre = nombre;
        this.apellido = apellido;
        this.apellido2 = apellido2;
        this.email = email;
        this.birthday = birthday;
        this.saint = saint;
        this.dni = dni;
        this.extras = Objects.requireNonNullElseGet(extras, ArrayList::new);
        this.sacraments = Objects.requireNonNullElseGet(sacraments, Sacraments::new);
        this.fileStorage = Objects.requireNonNullElseGet(fileStorage, ArrayList::new);
        this.createdOn = (createdOn == null) ? LocalDateTime.now() : createdOn;
    }*/


}
