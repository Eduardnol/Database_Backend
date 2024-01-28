package com.example.database_user.controllers.impl;

import com.example.database_user.controllers.api.PersonaAPI;
import com.example.database_user.controllers.dto.Persona.PersonaDTO;
import com.example.database_user.domain.service.PersonaService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/people")
public class PersonaController extends BaseController implements PersonaAPI {

  @Autowired
  private PersonaService personaServiceImplementation;

  @Override
  public ResponseEntity<List<PersonaDTO>> fetchAllPeople(
      Integer page,
      Integer size) {

    /*return new ResponseEntity<>(MainResponse.builder()
        .message("All people fetched successfully")
        .status(HttpStatus.OK)
        .details(personaServiceImplementation.fetchAllPeople(page, size))
        .build(),
        HttpStatus.OK);*/

    return new ResponseEntity<>(personaServiceImplementation.fetchAllPeople(page, size),
        HttpStatus.OK);
  }

  @Override
  public ResponseEntity<PersonaDTO> getById(String id) {
    return personaServiceImplementation.findPersonById(id);
  }

  @Override
  public ResponseEntity<List<PersonaDTO>> fetchPeopleByName(
      String name) {
    return personaServiceImplementation.fetchPeopleByName(name);
  }

  @Override
  public ResponseEntity<List<PersonaDTO>> fetchDateRangePeople(
      String initialDate,
      String finalDate) {

    return personaServiceImplementation.fetchBirthRangePeople(initialDate, finalDate);

  }

  @Override
  public ResponseEntity<String> insertNewUser(@RequestBody @Valid PersonaDTO person) {
    return personaServiceImplementation.insertNewPerson(person);
  }

  @Override
  public ResponseEntity<String> deleteUser(
      String identificador) {

    return personaServiceImplementation.deletePersonById(identificador);
  }

  @Override
  public ResponseEntity<String> updateExisting(
      PersonaDTO personaDTO) {

    //TODO what happens to update when we add the discipuladoMenores fields
    return personaServiceImplementation.updatePerson(personaDTO);

  }

  @Override
  public ResponseEntity<List<PersonaDTO>> sortPeopleByName(
      String field,
      String direction) {

    if (field.equals("name")) {
      return personaServiceImplementation.sortPeopleByName(direction);
    }
    if (field.equals("surname")) {
      return personaServiceImplementation.sortPeopleBySurname(direction);
    }
    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        if(identificador.equals("date")){
//
//        }
  }
}
