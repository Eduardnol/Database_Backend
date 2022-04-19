package com.example.database_user.dtos.Persona.Ninos;

import com.example.database_user.dtos.Custom;
import com.example.database_user.dtos.FileStorage;
import com.example.database_user.dtos.Persona.Persona;
import com.example.database_user.dtos.Sacraments;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;


@Document
@Data
public class InscritoNinos extends Persona {
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


	public InscritoNinos(String nombre, String apellido, String email, LocalDate birthday, LocalDate saint, String dni, ArrayList<Custom> extras, Sacraments sacraments, ArrayList<FileStorage> fileStorage, LocalDateTime createdOn, String padre, String madre) {

		super(nombre, apellido, email, birthday, saint, dni, extras, sacraments, fileStorage, createdOn);
		this.padre = padre;
		this.madre = madre;
	}


	public InscritoNinos(String padre, String madre, String comoHasConocidoParroquia, String numeroContacto, String mailContacto, boolean pagado, boolean autorizado, String colegio, String cursoActual) {

		this.padre = padre;
		this.madre = madre;
		this.comoHasConocidoParroquia = comoHasConocidoParroquia;
		this.numeroContacto = numeroContacto;
		this.mailContacto = mailContacto;
		this.pagado = pagado;
		this.autorizado = autorizado;
		this.colegio = colegio;
		this.cursoActual = cursoActual;
	}
}
