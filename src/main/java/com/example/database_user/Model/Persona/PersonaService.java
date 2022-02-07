package com.example.database_user.Model.Persona;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class PersonaService {
    private final PersonaRepository personaRepository;


    public List<Persona> fetchAllPeople() {

        return personaRepository.findAll();
    }


    public List<Persona> fetchBirthRangePeople(String start, String end) {

        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;
        List<Persona> queryResult = new ArrayList<>();

        personaRepository.findAllByBirthdayBetween(LocalDate.parse(start, formatter).minusDays(1)
                , LocalDate.parse(end, formatter).plusDays(1)).ifPresent(s -> {
            queryResult.addAll(s);
        });

        return queryResult;
    }
}
