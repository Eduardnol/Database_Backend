package com.example.database_user.controllers.impl;

import com.example.database_user.controllers.api.StatisticsAPI;
import com.example.database_user.controllers.dto.Persona.PersonaDTO;
import com.example.database_user.services.StatisticsService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/stats")
@AllArgsConstructor
public class StatisticsController implements StatisticsAPI {

  @Autowired
  private final StatisticsService statisticsService;

  @Override
  public ResponseEntity<Integer> countAllPersons() {

    return ResponseEntity.status(HttpStatus.OK).body(statisticsService.countAllPeople());
  }

  @Override
  public ResponseEntity<List<PersonaDTO>> getTodayBirthday() {

    return statisticsService.getTodayBirthdays();
  }

}
