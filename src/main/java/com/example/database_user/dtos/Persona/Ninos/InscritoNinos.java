package com.example.database_user.dtos.Persona.Ninos;

import com.example.database_user.dtos.Custom;
import com.example.database_user.dtos.FileStorage;
import com.example.database_user.dtos.Persona.Persona;
import com.example.database_user.dtos.Sacraments;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Data
@EqualsAndHashCode(callSuper = true)
public class InscritoNinos extends Persona {

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

	InnerIncritoNinos infoInscripcionMenor;


	//Constructor solo esta clase
	public InscritoNinos(String padre, String madre, String comoHasConocidoParroquia, String numeroContacto, String mailContacto, boolean pagado, boolean autorizado, String colegio, String cursoActual) {

		this.infoInscripcionMenor = new InnerIncritoNinos(padre, madre, comoHasConocidoParroquia, numeroContacto, mailContacto, pagado, autorizado, colegio, cursoActual);

	}


	//Constructor con superclase
	public InscritoNinos(String nombre, String apellido, String email, LocalDate birthday, LocalDate saint, String dni, ArrayList<Custom> extras, Sacraments sacraments, ArrayList<FileStorage> fileStorage, LocalDateTime createdOn, InnerIncritoNinos infoInscripcionMenor) {

		super(nombre, apellido, email, birthday, saint, dni, extras, sacraments, fileStorage, createdOn);
		this.infoInscripcionMenor = infoInscripcionMenor;
	}
}
