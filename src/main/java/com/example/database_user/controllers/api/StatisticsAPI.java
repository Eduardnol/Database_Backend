package com.example.database_user.controllers.api;

import com.example.database_user.model.dto.Persona.PersonaDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "Statistics", description = "Gets statistics from the database")
@RequestMapping(path = "api/v1/stats")
public interface StatisticsAPI {

  @GetMapping("/count_persons")
  ResponseEntity<Integer> countAllPersons();

  @GetMapping("/today_birthday")
  ResponseEntity<List<PersonaDTO>> getTodayBirthday();

}
