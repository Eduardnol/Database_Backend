package com.example.database_user.controllers.dto.Persona;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PersonaNinos {

    //TODO puede que nos sirva mas una subclase
    private String padre;
    private String madre;
    private String comoHasConocidoParroquia;
    private String numeroContacto;
    private String mailContacto;
    private boolean pagado;
    private boolean autorizado;
    private String colegio;
    private String cursoActual;

}

