package com.example.database_user.Model.Persona;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/people")
@AllArgsConstructor
public class PersonaController {
    private final PersonaService personaService;

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/allpeople")
    public ResponseEntity<List<Persona>> fetchAllPeople() {

        return ResponseEntity.status(HttpStatus.OK).body(personaService.fetchAllPeople());
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping(value = "/search/{name}")
    public ResponseEntity<List<Persona>> fetchPeopleByName(@PathVariable("name") String name) {

        return personaService.fetchPeopleByName(name);
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping(value = "/daterange/{initial}/{final}")
    public ResponseEntity<List<Persona>> fetchDateRangePeople(@PathVariable("initial") String initialDate, @PathVariable("final") String finalDate) {

        return personaService.fetchBirthRangePeople(initialDate, finalDate);

    }

    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping(value = "/insertnew/")
    public ResponseEntity<String> insertNewUser(@RequestBody Persona persona) {

        return personaService.insertNewPerson(persona);

    }

    @CrossOrigin(origins = "http://localhost:8080")
    @DeleteMapping(value = "/deletebyid")
    public ResponseEntity<String> deleteUser(@RequestParam(name = "id") String identificador) {

        return personaService.deletePerson(identificador);
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @PutMapping(value = "/update/")
    public ResponseEntity<String> updateExisting(@RequestBody Persona persona) {

        return personaService.updatePerson(persona);

    }

}
