package com.example.database_user.controllers;


import com.example.database_user.dtos.Lifeteen;
import com.example.database_user.services.LifeteenService;
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

		return lifeteenService.deleteById(id);
	}


	@PutMapping("/update")
	public ResponseEntity<String> updateLifeteen(@RequestBody @Valid Lifeteen lifeteen) {

		return lifeteenService.updateLifeteen(lifeteen);
	}

}
