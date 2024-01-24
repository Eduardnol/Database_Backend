package com.example.database_user.domain.service;

import com.example.database_user.controllers.dto.Persona.PersonaDTO;
import java.util.List;
import org.springframework.http.ResponseEntity;

public interface PersonaService {

  List<PersonaDTO> fetchAllPeople(Integer page, Integer size);

  ResponseEntity<PersonaDTO> findPersonById(String id);

  ResponseEntity<List<PersonaDTO>> fetchPeopleByName(String name);

  ResponseEntity<List<PersonaDTO>> fetchBirthRangePeople(String start, String end);

  ResponseEntity<String> insertNewPerson(PersonaDTO person);

  ResponseEntity<String> deletePersonById(String personID);

  ResponseEntity<String> updatePerson(PersonaDTO person);

  ResponseEntity<List<PersonaDTO>> sortPeopleByName(String sort);

  ResponseEntity<List<PersonaDTO>> sortPeopleBySurname(String sort);


}
