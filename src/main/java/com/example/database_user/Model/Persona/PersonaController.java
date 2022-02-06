package com.example.database_user.Model.Persona;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/people")
@AllArgsConstructor
public class PersonaController {
    private final PersonaService personaService;


    @GetMapping("/allpeople")
    public ResponseEntity<List<Persona>> fetchAllPeople() {

        return ResponseEntity.status(HttpStatus.OK).body(personaService.fetchAllPeople());
    }


    @GetMapping(value = "/daterange/{initial}/{final}")
    public ResponseEntity<List<Persona>> fetchDateRangePeople(@PathVariable("initial") String initialDate, @PathVariable("final") String finalDate) {

        return ResponseEntity.status(HttpStatus.OK).body(personaService.fetchDateRangePeople(initialDate, finalDate));

    }

}
