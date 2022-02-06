package com.example.database_user.Model.Persona;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/persona")
@AllArgsConstructor
public class PersonaController {
    private final PersonaService personaService;


    @GetMapping
    public List<Persona> fetchAllPeople() {

        return personaService.fetchAllPeople();
    }
}
