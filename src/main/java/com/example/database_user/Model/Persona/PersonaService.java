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


    public List<Persona> fetchDateRangePeople(String start, String end) {

        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;
        List<Persona> queryResult = new ArrayList<>();
        personaRepository.findAllByBirthdayBetween(LocalDate.parse(start, formatter)
                , LocalDate.parse(end, formatter)).ifPresent(s -> {
            queryResult.addAll(s);
        });
        return queryResult;
    }
}
