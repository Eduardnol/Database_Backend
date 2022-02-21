package com.example.database_user.Model.Persona;

import com.example.database_user.Model.Common.FileStorage;
import com.example.database_user.Model.Custom;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

@Data
@Document
public class Persona {
    @Id
    private String id;
    @TextIndexed(weight = 3)
    private String nombre;
    @TextIndexed(weight = 2)
    private String apellido;
    @TextIndexed(weight = 2)
    private String apellido2;
    @TextIndexed
    private String email;
    private LocalDate birthday;
    private LocalDate saint;
    private String dni;
    private ArrayList<Custom> extras;
    private Sacraments sacraments;
    private ArrayList<FileStorage> fileStorage;


    public Persona(String nombre, String apellido, String email, LocalDate birthday, LocalDate saint, String dni, ArrayList<Custom> extras, Sacraments sacraments, ArrayList<FileStorage> fileStorage) {

        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.birthday = birthday;
        this.saint = saint;
        this.dni = dni;
        this.extras = Objects.requireNonNullElseGet(extras, ArrayList::new);
        this.sacraments = Objects.requireNonNullElseGet(sacraments, Sacraments::new);
        this.fileStorage = Objects.requireNonNullElseGet(fileStorage, ArrayList::new);
    }
}
