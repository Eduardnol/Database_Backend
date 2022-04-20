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
	private String responsable1;
	private String responsable2;
	private LocalDate startDate;
	private List<String> idMonitores;
	private List<String> IdInscritos;

}
