package com.example.database_user.controllers;

import com.example.database_user.controllers.api.PersonaAPI;
import com.example.database_user.controllers.dto.Persona.PersonaDTO;
import com.example.database_user.controllers.impl.BaseController;
import com.example.database_user.services.PersonaServiceImplementation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/people")
public class PersonaController extends BaseController implements PersonaAPI {

  @Autowired
  private PersonaServiceImplementation personaServiceImplementation;

  @Override
  @GetMapping("/allpeople")
  public ResponseEntity<List<PersonaDTO>> fetchAllPeople(
      @RequestParam(defaultValue = "0") Integer page,
      @RequestParam(defaultValue = "10") Integer size) {

    return ResponseEntity.status(HttpStatus.OK).body(
        personaServiceImplementation.fetchAllPeople(page, size));
  }

  @Override
  @GetMapping("/getbyid/{id}")
  public ResponseEntity<PersonaDTO> getById(@PathVariable("id") String id) {
    return personaServiceImplementation.findPersonById(id);
  }

  @Override
  @GetMapping(value = "/search/{name}")
  public ResponseEntity<List<PersonaDTO>> fetchPeopleByName(
      @Parameter(description = "Name of the person to be " +
          "searched") @PathVariable("name") String name) {

    return personaServiceImplementation.fetchPeopleByName(name);
  }

  @Override
  @GetMapping(value = "/daterange/{initial}/{final}")
  public ResponseEntity<List<PersonaDTO>> fetchDateRangePeople(
      @Parameter(description = "Initial value of the date") @PathVariable("initial") String initialDate,
      @Parameter(description = "Final value of the date") @PathVariable("final") String finalDate) {

    return personaServiceImplementation.fetchBirthRangePeople(initialDate, finalDate);

  }

  @Override
  @PostMapping(value = "/insertnew/")
  public ResponseEntity<String> insertNewUser(@RequestBody @Valid PersonaDTO person) {
    PersonaDTO newBuiltPersonaDTO = person;
    return personaServiceImplementation.insertNewPerson(newBuiltPersonaDTO);

  }

  @Override
  @DeleteMapping(value = "/deletebyid")
  public ResponseEntity<String> deleteUser(
      @Parameter(description = "Id of the user to be deleted") @RequestParam(name = "id") String identificador) {

    return personaServiceImplementation.deletePersonById(identificador);
  }

  @Override
  @PutMapping(value = "/update/")
  public ResponseEntity<String> updateExisting(
      @Parameter(description = "Person with the new info to be updated") @RequestBody PersonaDTO personaDTO) {

    //TODO what happens to update when we add the discipuladoMenores fields
    return personaServiceImplementation.updatePerson(personaDTO);

  }

  @Override
  @GetMapping(value = "/sort")
  public ResponseEntity<List<PersonaDTO>> sortPeopleByName(
      @Parameter(description = "specify ordening field (name or surname)") @RequestParam(name = "field") String field,
      @Parameter(description = "Specify direction (asc or desc)") @RequestParam(name = "direction") String direction) {

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
