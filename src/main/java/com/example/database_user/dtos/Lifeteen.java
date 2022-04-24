package com.example.database_user.dtos;


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
public class Lifeteen {
    @Id
    private String id;
    private String title;
    private String responsable1;
    private String responsable2;
    private LocalDate startDate;
    private Integer numInscritos;
    private List<String> idMonitores;
    private List<String> idInscritos;

}
