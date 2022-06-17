package com.example.database_user.dtos.Persona;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SimplePersona {
    private String id;
    private String nombre;
    private String apellido;
}
