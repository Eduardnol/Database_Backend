package com.example.database_user.controllers.api;

import com.example.database_user.model.dto.Persona.PersonaDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "Persona", description = "Obtain all information relative to the users")
@RequestMapping(path = "api/v1/people")
public interface PersonaAPI {

  @Operation(summary = "Get all users registered")
  @GetMapping("/allpeople")
  ResponseEntity<List<PersonaDTO>> fetchAllPeople(
      @RequestParam(defaultValue = "0") Integer page,
      @RequestParam(defaultValue = "10") Integer size);

  @Operation(summary = "Get a user by id")
  @GetMapping("/getbyid/{id}")
  ResponseEntity<PersonaDTO> getById(@PathVariable("id") String id);

  @Operation(summary = "Search for users with specific name")
  @GetMapping(value = "/search/{name}")
  ResponseEntity<List<PersonaDTO>> fetchPeopleByName(
      @Parameter(description = "Name of the person to be " +
          "searched") @PathVariable("name") String name);

  @Operation(summary = "Get all users filtered by date")
  @GetMapping(value = "/daterange/{initial}/{final}")
  ResponseEntity<List<PersonaDTO>> fetchDateRangePeople(
      @Parameter(description = "Initial value of the date") @PathVariable("initial") String initialDate,
      @Parameter(description = "Final value of the date") @PathVariable("final") String finalDate);

  @Operation(summary = "Insert a new user")
  @PostMapping(value = "/insertnew")
  ResponseEntity<String> insertNewUser(@RequestBody @Valid PersonaDTO person);

  @Operation(summary = "Delete a user by id")
  @DeleteMapping(value = "/deletebyid/{id}")
  ResponseEntity<String> deleteUser(
      @Parameter(description = "Id of the user to be deleted") @PathVariable(name = "id") String identificador);


  @Operation(summary = "Update a specific user")
  @PutMapping(value = "/update")
  ResponseEntity<String> updateExisting(
      @Parameter(description = "Person with the new info to be updated") @RequestBody PersonaDTO personaDTO);

  @Operation(summary = "Sort all users")
  @GetMapping(value = "/sort")
    //@PreAuthorize("hasAuthority('ADMIN')")
  ResponseEntity<List<PersonaDTO>> sortPeopleByName(
      @Parameter(description = "specify ordening field (name or surname)") @RequestParam(name = "field") String field,
      @Parameter(description = "Specify direction (asc or desc)") @RequestParam(name = "direction") String direction);

}
