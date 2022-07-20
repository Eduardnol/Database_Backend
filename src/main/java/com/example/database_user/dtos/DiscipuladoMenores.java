package com.example.database_user.dtos;


import com.example.database_user.dtos.Persona.SimplePersona;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Data
@Document
@AllArgsConstructor
@NoArgsConstructor
public class DiscipuladoMenores {
    @Id
    private String id;
    private String title;
    private SimplePersona responsable1;
    private SimplePersona responsable2;
    private LocalDate startDate;
    private Integer numInscritos;
    private List<SimplePersona> idMonitores;
    private List<SimplePersona> idInscritos;

}
