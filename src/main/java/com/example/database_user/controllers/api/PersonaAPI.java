package com.example.database_user.controllers.api;

import com.example.database_user.controllers.dto.Persona.PersonaDTO;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Api(value = "PersonaAPI", description = "API de Persona")
public interface PersonaAPI {

  @Operation(summary = "Get all users registered")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Found all Users",
          content = {
              @Content(mediaType = "application/json",
                  schema = @Schema(implementation = PersonaDTO.class))})
  })
  @GetMapping("/allpeople")
  ResponseEntity<List<PersonaDTO>> fetchAllPeople(
      @RequestParam(defaultValue = "0") Integer page,
      @RequestParam(defaultValue = "10") Integer size);

  @GetMapping("/getbyid/{id}")
  ResponseEntity<PersonaDTO> getById(@PathVariable("id") String id);

  @Operation(summary = "Search for users with specific name")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Found the user",
          content = {
              @Content(mediaType = "application/json",
                  schema = @Schema(implementation = PersonaDTO.class))})
  })
  @GetMapping(value = "/search/{name}")
  ResponseEntity<List<PersonaDTO>> fetchPeopleByName(
      @Parameter(description = "Name of the person to be " +
          "searched") @PathVariable("name") String name);

  @Operation(summary = "Get all users filtered by date")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Found all Users",
          content = {
              @Content(mediaType = "application/json",
                  schema = @Schema(implementation = PersonaDTO.class))})
  })
  @GetMapping(value = "/daterange/{initial}/{final}")
  ResponseEntity<List<PersonaDTO>> fetchDateRangePeople(
      @Parameter(description = "Initial value of the date") @PathVariable("initial") String initialDate,
      @Parameter(description = "Final value of the date") @PathVariable("final") String finalDate);

  @Operation(summary = "Insert a new user")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "User correctly inserted",
          content = {
              @Content(mediaType = "application/json",
                  schema = @Schema(implementation = PersonaDTO.class))})
  })
  @PostMapping(value = "/insertnew/")
  ResponseEntity<String> insertNewUser(@RequestBody @Valid PersonaDTO person);

  @Operation(summary = "Delete a user by id")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Deleted user",
          content = {
              @Content(mediaType = "application/json",
                  schema = @Schema(implementation = PersonaDTO.class))})})

  @DeleteMapping(value = "/deletebyid")
  ResponseEntity<String> deleteUser(
      @Parameter(description = "Id of the user to be deleted") @RequestParam(name = "id") String identificador);


  @Operation(summary = "Update a specific user")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Updated the user",
          content = {
              @Content(mediaType = "application/json",
                  schema = @Schema(implementation = PersonaDTO.class))})
  })

  @PutMapping(value = "/update/")
  ResponseEntity<String> updateExisting(
      @Parameter(description = "Person with the new info to be updated") @RequestBody PersonaDTO personaDTO);

  @Operation(summary = "Sort all users")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Sorted all Users",
          content = {
              @Content(mediaType = "application/json",
                  schema = @Schema(implementation = PersonaDTO.class))})
  })
  @GetMapping(value = "/sort")
  ResponseEntity<List<PersonaDTO>> sortPeopleByName(
      @Parameter(description = "specify ordening field (name or surname)") @RequestParam(name = "field") String field,
      @Parameter(description = "Specify direction (asc or desc)") @RequestParam(name = "direction") String direction);

}
