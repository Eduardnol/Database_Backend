package com.example.database_user.model.dto;


import com.example.database_user.model.dto.Persona.SimplePersona;
import com.example.database_user.model.dto.Subgrupo.Subgrupo;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiscipuladoMenoresDTO {

  private String id;
  private String title;
  private List<SimplePersona> responsables;
  private LocalDate startDate;
  private Integer numInscritos;
  private List<SimplePersona> idMonitores;
  private List<SimplePersona> idInscritos;
  private List<Subgrupo> subgrupos;

}
