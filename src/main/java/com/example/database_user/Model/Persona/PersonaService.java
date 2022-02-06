package com.example.database_user.Model.Persona;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class PersonaService {
    private final PersonaRepository personaRepository;


    public List<Persona> fetchAllPeople() {

        return personaRepository.findAll();
    }
}
