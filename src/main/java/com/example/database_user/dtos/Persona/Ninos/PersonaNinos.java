package com.example.database_user.dtos.Persona.Ninos;

import com.example.database_user.dtos.Custom;
import com.example.database_user.dtos.FileStorage;
import com.example.database_user.dtos.Persona.Persona;
import com.example.database_user.dtos.Sacraments;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Data
@EqualsAndHashCode(callSuper = true)
public class PersonaNinos extends Persona {

    InnerIncritoNinos infoInscripcionMenor;


    //Constructor solo esta clase
    public PersonaNinos(InnerIncritoNinos infoInscripcionMenor) {

        this.infoInscripcionMenor = infoInscripcionMenor;

    }


    //Constructor con superclase
    public PersonaNinos(String nombre, String apellido, String apellido2, String email, LocalDate birthday, LocalDate saint, String dni, ArrayList<Custom> extras, Sacraments sacraments, ArrayList<FileStorage> fileStorage, LocalDateTime createdOn, InnerIncritoNinos infoInscripcionMenor) {

        super(nombre, apellido, apellido2, email, birthday, saint, dni, extras, sacraments, fileStorage, createdOn);
        this.infoInscripcionMenor = infoInscripcionMenor;
    }


    @AllArgsConstructor
    @Data
    public static class InnerIncritoNinos {
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
}
