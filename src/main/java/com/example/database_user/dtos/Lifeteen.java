package com.example.database_user.dtos;


import com.example.database_user.dtos.Persona.Persona;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.util.List;

@Data
public class Lifeteen {
	@Id
	private String id;
	private Persona responsable1;
	private Persona responsable2;
	private LocalDate startDate;
	private List<Persona> monitores;
	private List<String> IdInscritos;

}
