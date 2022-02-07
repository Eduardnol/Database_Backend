package com.example.database_user.Model.Persona;

import lombok.AllArgsConstructor;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class PersonaService {
    private final PersonaRepository personaRepository;
    private static final Logger logger = LogManager.getLogger(PersonaService.class);


    public List<Persona> fetchAllPeople() {

        return personaRepository.findAll();
    }


    public ResponseEntity<List<Persona>> fetchBirthRangePeople(String start, String end) {

        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;
        List<Persona> queryResult = new ArrayList<>();
        LocalDate startDate = LocalDate.parse(start, formatter);
        LocalDate endDate = LocalDate.parse(end, formatter);
        HttpStatus status = HttpStatus.ACCEPTED;

        if (endDate.isBefore(startDate)) {
            //Error
            status = HttpStatus.BAD_REQUEST;
            logger.log(Level.WARN, "End date is before start date");
        } else {
            personaRepository.findAllByBirthdayBetween(startDate.minusDays(1)
                    , endDate.plusDays(1)).ifPresent(s -> {
                queryResult.addAll(s);
            });
            status = HttpStatus.OK;
        }

        return new ResponseEntity<List<Persona>>(queryResult, status);

    }
}
