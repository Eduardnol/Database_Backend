package com.example.database_user.dtos;

import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;


@Document
public class PersonaInscritoLifeteen extends Persona {
	private String padre;
	private String madre;
	private String comoHasConocidoParroquia;
	private String numeroContacto;
	private String mailContacto;
	private boolean pagado;
	private boolean autorizaciones;
	private String colegio;
	private String cursoActual;



	public PersonaInscritoLifeteen(String nombre, String apellido, String email, LocalDate birthday, LocalDate saint, String dni, ArrayList<Custom> extras, Sacraments sacraments, ArrayList<FileStorage> fileStorage, LocalDateTime createdOn, String padre, String madre) {

		super(nombre, apellido, email, birthday, saint, dni, extras, sacraments, fileStorage, createdOn);
		this.padre = padre;
		this.madre = madre;
	}


	public PersonaInscritoLifeteen(String padre, String madre) {

		super();

		this.padre = padre;
		this.madre = madre;
	}
}
