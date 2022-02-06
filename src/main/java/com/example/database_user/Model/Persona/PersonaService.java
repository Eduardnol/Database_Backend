package com.example.database_user.Model.Persona;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Service
public class PersonaService {
    private final PersonaRepository personaRepository;


    public List<Persona> fetchAllPeople() {

        return personaRepository.findAll();
    }


    public List<Persona> fetchDateRangePeople(LocalDate start, LocalDate end) {

        return personaRepository.findPersonaByBirthdayIsBetween(start, end).stream().toList();
    }
}
