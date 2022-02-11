package com.example.database_user.Model.Persona;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Document
public class Persona {
    @Id
    private String id;
    @TextIndexed(weight = 3)
    private String nombre;
    @TextIndexed(weight = 2)
    private String apellido;
    private String apellido2;
    @TextIndexed
    private String email;
    private LocalDate birthday;
    private LocalDate saint;
    private String dni;
    // private Map<String, String> map;


    public Persona(String nombre, String apellido, String email, LocalDate birthday, LocalDate saint, String dni) {

        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.birthday = birthday;
        this.saint = saint;
        this.dni = dni;
    }
}
