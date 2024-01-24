package com.example.database_user.controllers.dto;


import com.example.database_user.controllers.dto.Persona.SimplePersona;
import com.example.database_user.controllers.dto.Subgrupo.Subgrupo;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@Document
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DiscipuladoMenores {

  @Id
  private String id;
  private String title;
  private List<SimplePersona> responsables;
  @JsonFormat(pattern = "yyyy-MM-dd")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate startDate;
  private Integer numInscritos;
  private List<SimplePersona> idMonitores;
  private List<SimplePersona> idInscritos;
  private List<Subgrupo> subgrupos;

}