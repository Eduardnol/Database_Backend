package com.example.database_user.domain.service;

import com.example.database_user.controllers.dto.Persona.PersonaDTO;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface StatisticsService {

  Integer countAllPeople();

  ResponseEntity<List<PersonaDTO>> getTodayBirthdays();

}
