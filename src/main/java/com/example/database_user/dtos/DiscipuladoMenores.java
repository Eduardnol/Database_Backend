package com.example.database_user.dtos;


import com.example.database_user.dtos.Persona.SimplePersona;
import com.example.database_user.dtos.Subgrupo.Subgrupo;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@AllArgsConstructor
@NoArgsConstructor
public class DiscipuladoMenores {

    @Id
    private String id;
    private String title;
    private List<SimplePersona> responsables;
    private LocalDate startDate;
    private Integer numInscritos;
    private List<SimplePersona> idMonitores;
    private List<SimplePersona> idInscritos;
    private List<Subgrupo> subgrupos;

}
