package com.example.database_user.controllers;


import com.example.database_user.dtos.Lifeteen;
import com.example.database_user.services.LifeteenService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/lifeteen")
@AllArgsConstructor
public class LifeteenController {
	private final LifeteenService lifeteenService;

	public ResponseEntity<List<Lifeteen>> fetchAllLifeteen() {

		return lifeteenService.fetchAllLifeteen();
	}

}
