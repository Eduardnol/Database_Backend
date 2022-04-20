package com.example.database_user.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Lifeteen {
	@Id
	private String id;
	private String responsable1;
	private String responsable2;
	private LocalDate startDate;
	private List<String> idMonitores;
	private List<String> idInscritos;

	public Lifeteen(String responsable1, String responsable2, LocalDate startDate, List<String> idMonitores, List<String> idInscritos) {
		this.responsable1 = responsable1;
		this.responsable2 = responsable2;
		this.startDate = startDate;
		this.idMonitores = idMonitores;
		this.idInscritos = idInscritos;
	}
}
