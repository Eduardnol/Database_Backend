package com.example.database_user.repositories.entity;

import com.example.database_user.controllers.dto.Persona.SimplePersona;
import com.example.database_user.controllers.dto.Subgrupo.Subgrupo;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;
@Data
@Document
@AllArgsConstructor
@NoArgsConstructor
public class DiscipuladoMenoresEntity {
    @Id
    @Field("_id")
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
