package com.example.database_user.dtos;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Data
@Document
public class Lifeteen {
	@Id
	private String id;
	private Persona responsable1;
	private Persona responsable2;
	private LocalDate startDate;
	private List<Persona> monitores;
	private List<Persona> inscritos;

}
