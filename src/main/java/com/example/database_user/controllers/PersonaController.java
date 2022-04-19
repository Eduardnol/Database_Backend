package com.example.database_user.controllers;

import com.example.database_user.dtos.Persona.Persona;
import com.example.database_user.services.PersonaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/people")
@AllArgsConstructor
public class PersonaController {
	private final PersonaService personaService;

	@Operation(summary = "Get all users registered")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Found all Users",
					content = {@Content(mediaType = "application/json",
							schema = @Schema(implementation = Persona.class))})
	})
	@GetMapping("/allpeople")
	public ResponseEntity<List<Persona>> fetchAllPeople() {

		return ResponseEntity.status(HttpStatus.OK).body(personaService.fetchAllPeople());
	}


	@Operation(summary = "Search for users with specific name")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Found the user",
					content = {@Content(mediaType = "application/json",
							schema = @Schema(implementation = Persona.class))})
	})
	@GetMapping(value = "/search/{name}")
	public ResponseEntity<List<Persona>> fetchPeopleByName(@Parameter(description = "Name of the person to be searched") @PathVariable("name") String name) {

		return personaService.fetchPeopleByName(name);
	}


	@Operation(summary = "Get all users filtered by date")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Found all Users",
					content = {@Content(mediaType = "application/json",
							schema = @Schema(implementation = Persona.class))})
	})
	@GetMapping(value = "/daterange/{initial}/{final}")
	public ResponseEntity<List<Persona>> fetchDateRangePeople(@Parameter(description = "Initial value of the date") @PathVariable("initial") String initialDate, @Parameter(description = "Final value of the date") @PathVariable("final") String finalDate) {

		return personaService.fetchBirthRangePeople(initialDate, finalDate);

	}


	@Operation(summary = "Insert a new user")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "User correctly inserted",
					content = {@Content(mediaType = "application/json",
							schema = @Schema(implementation = Persona.class))})
	})
	@PostMapping(value = "/insertnew/")
	public ResponseEntity<String> insertNewUser(@RequestBody @Valid Persona persona) {

		return personaService.insertNewPerson(persona);

	}


	@Operation(summary = "Delete a user by id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Deleted user",
					content = {@Content(mediaType = "application/json",
							schema = @Schema(implementation = Persona.class))})})

	@DeleteMapping(value = "/deletebyid")
	public ResponseEntity<String> deleteUser(@Parameter(description = "Id of the user to be deleted") @RequestParam(name = "id") String identificador) {

		return personaService.deletePerson(identificador);
	}


	@Operation(summary = "Update a specific user")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Updated the user",
					content = {@Content(mediaType = "application/json",
							schema = @Schema(implementation = Persona.class))})
	})

	@PutMapping(value = "/update/")
	public ResponseEntity<String> updateExisting(@Parameter(description = "Person with the new info to be updated") @RequestBody Persona persona) {

		return personaService.updatePerson(persona);

	}


	@Operation(summary = "Sort all users")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Sorted all Users",
					content = {@Content(mediaType = "application/json",
							schema = @Schema(implementation = Persona.class))})
	})
	@GetMapping(value = "/sort")
	public ResponseEntity<List<Persona>> sortPeopleByName(@Parameter(description = "specify ordening field (name or surname)") @RequestParam(name = "field") String field, @Parameter(description = "Specify direction (asc or desc)") @RequestParam(name = "direction") String direction) {

		if (field.equals("name")) {
			return personaService.sortPeopleByName(direction);
		}
		if (field.equals("surname")) {
			return personaService.sortPeopleBySurname(direction);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        if(identificador.equals("date")){
//
//        }
	}
}
