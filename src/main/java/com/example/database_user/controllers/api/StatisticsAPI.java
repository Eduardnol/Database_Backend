package com.example.database_user.controllers.api;

import com.example.database_user.controllers.dto.Persona.PersonaDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@Tag(name = "Statistics", description = "Gets statistics from the database")
public interface StatisticsAPI {

  @GetMapping("/count_persons")
  ResponseEntity<Integer> countAllPersons();

  @GetMapping("/today_birthday")
  ResponseEntity<List<PersonaDTO>> getTodayBirthday();

}
