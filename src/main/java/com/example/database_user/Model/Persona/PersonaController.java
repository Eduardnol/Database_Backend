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


    @GetMapping("/allpeople")
    public ResponseEntity<List<Persona>> fetchAllPeople() {

        return ResponseEntity.status(HttpStatus.OK).body(personaService.fetchAllPeople());
    }


    @GetMapping(value = "/search/{name}")
    public ResponseEntity<List<Persona>> fetchPeopleByName(@PathVariable("name") String name) {

        return personaService.fetchPeopleByName(name);
    }


    @GetMapping(value = "/daterange/{initial}/{final}")
    public ResponseEntity<List<Persona>> fetchDateRangePeople(@PathVariable("initial") String initialDate, @PathVariable("final") String finalDate) {

        return personaService.fetchBirthRangePeople(initialDate, finalDate);

    }


    @PostMapping(value = "/insertnew/")
    public ResponseEntity<String> insertNewUser(@RequestBody Persona persona) {

        return personaService.insertNewPerson(persona);

    }


    @DeleteMapping(value = "/deletebyid")
    public ResponseEntity<String> deleteUser(@RequestParam(name = "id") String identificador) {

        return personaService.deletePerson(identificador);
    }


    @PutMapping(value = "/update/")
    public ResponseEntity<String> updateExisting(@RequestBody Persona persona) {

        return personaService.updatePerson(persona);

    }


    /**
     * @param field     Field to be sorted by the database
     * @param direction Direction must be ASC or DESC (else is default DESC)
     * @return
     */
    @GetMapping(value = "/sort")
    public ResponseEntity<List<Persona>> sortPeopleByName(@RequestParam(name = "field") String field, @RequestParam(name = "direction") String direction) {

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
