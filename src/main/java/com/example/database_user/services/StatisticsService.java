package com.example.database_user.services;

import com.example.database_user.model.dto.Persona.PersonaDTO;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface StatisticsService {

  Integer countAllPeople();

  ResponseEntity<List<PersonaDTO>> getTodayBirthdays();

}
