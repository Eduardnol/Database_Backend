package com.example.database_user.dtos;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

@Data
@Document
public class Persona {
	@Id
	private String id;
	@TextIndexed(weight = 3)
	@NotNull(message = "The name is required.")
	private String nombre;
	@TextIndexed(weight = 2)
	@NotNull(message = "The surname is required.")
	private String apellido;
	@TextIndexed(weight = 2)
	@NotNull(message = "The surname2 is required.")
	private String apellido2;
	@TextIndexed
	@Email(message = "The email address is invalid.", flags = {Pattern.Flag.CASE_INSENSITIVE})
	private String email;
	@DateTimeFormat
	@Past(message = "The date of birth must be in the past.")
	private LocalDate birthday;
	@DateTimeFormat
	@PastOrPresent(message = "The saint must be past or present")
	private LocalDate saint;
	private String dni;
	private ArrayList<Custom> extras;
	private Sacraments sacraments;
	private ArrayList<FileStorage> fileStorage;
	private LocalDate createdOn;


	public Persona(String nombre, String apellido, String email, LocalDate birthday, LocalDate saint, String dni, ArrayList<Custom> extras, Sacraments sacraments, ArrayList<FileStorage> fileStorage, LocalDate createdOn) {

		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.birthday = birthday;
		this.saint = saint;
		this.dni = dni;
		this.extras = Objects.requireNonNullElseGet(extras, ArrayList::new);
		this.sacraments = Objects.requireNonNullElseGet(sacraments, Sacraments::new);
		this.fileStorage = Objects.requireNonNullElseGet(fileStorage, ArrayList::new);
		this.createdOn = (createdOn == null) ? LocalDate.now() : createdOn;
	}
}
