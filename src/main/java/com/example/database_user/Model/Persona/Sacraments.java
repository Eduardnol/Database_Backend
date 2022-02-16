package com.example.database_user.Model.Persona;

import lombok.Data;

@Data
public class Sacraments {
    Boolean bautismo;
    Boolean comunion;
    Boolean confirmacion;
    Boolean matrimonio;


    public Sacraments() {

        this.bautismo = false;
        this.comunion = false;
        this.confirmacion = false;
        this.matrimonio = false;
    }
}
