package com.example.database_user.controllers;


import com.example.database_user.dtos.Lifeteen;
import com.example.database_user.dtos.Persona.Ninos.InscritoNinos;
import com.example.database_user.services.LifeteenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/lifeteen")
@AllArgsConstructor
public class LifeteenController {
	private final LifeteenService lifeteenService;


	@GetMapping("/all")
	public ResponseEntity<List<Lifeteen>> fetchAllLifeteen() {

		return lifeteenService.fetchAllLifeteen();
	}


	@PostMapping("/insert")
	public ResponseEntity<String> insertNewLifeteen(@RequestBody @Valid Lifeteen lifeteen) {

		return lifeteenService.insertNewLifeteen(lifeteen);
	}


	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteLifeteen(@PathVariable String id) {

		return lifeteenService.deleteLifeteenById(id);
	}


	@PutMapping("/update")
	public ResponseEntity<String> updateLifeteen(@RequestBody @Valid Lifeteen lifeteen) {

		return lifeteenService.updateLifeteen(lifeteen);
	}

	@Operation(summary = "Insert a new inscription to one lifeteen")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "User correctly inscribed",
					content = {@Content(mediaType = "application/json",
							schema = @Schema(implementation = InscritoNinos.class))})
	})
	@PostMapping("/insert-inscription/{idlifeteen}")
	public ResponseEntity<String> inserNewInscription(@Parameter(description = "The one to be inserted")@RequestBody @Valid InscritoNinos inscritoNinos, @Parameter(description = "Id of the Lifeteen where we want to insert it") @PathVariable String idlifeteen) {

		return lifeteenService.addNewUserNewInsciption(inscritoNinos, idlifeteen);
	}



}
