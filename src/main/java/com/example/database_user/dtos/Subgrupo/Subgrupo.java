package com.example.database_user.dtos.Subgrupo;

import com.example.database_user.dtos.Persona.SimplePersona;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Subgrupo {
    private String titulo;
    private List<SimplePersona> responsables;
    private List<SimplePersona> integrantes;
    private String descripcion;
}
