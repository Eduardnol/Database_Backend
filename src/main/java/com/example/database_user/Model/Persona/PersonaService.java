package com.example.database_user.Model.Persona;

import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.core.query.TextQuery;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
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
    @Autowired
    private MongoTemplate mongoTemplate;


    public List<Persona> fetchAllPeople() {


        return personaRepository.findAll();

    }


    public ResponseEntity<List<Persona>> fetchPeopleByName(String name) {

        HttpStatus status = HttpStatus.ACCEPTED;
        TextCriteria criteria = TextCriteria
                .forDefaultLanguage()
                .matching(name);

        Query query = TextQuery.queryText(criteria).sortByScore();
        List<Persona> posts = mongoTemplate.find(query, Persona.class);

        status = HttpStatus.OK;
        logger.info("Retrieved users by name");

        return new ResponseEntity<List<Persona>>(posts, status);

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
            logger.warn("End date is before start date");
        } else {
            personaRepository.findAllByBirthdayBetween(startDate.minusDays(1)
                    , endDate.plusDays(1)).ifPresent(s -> {
                queryResult.addAll(s);
            });
            status = HttpStatus.OK;
            logger.info("Retrieved users by datarange");
        }
        return new ResponseEntity<List<Persona>>(queryResult, status);

    }


    public ResponseEntity<String> insertNewPerson(Persona person) {

        logger.info("Inserting new person");

        personaRepository.insert(person);

        return new ResponseEntity<>(HttpStatus.CREATED);

    }


    public ResponseEntity<String> deletePerson(String personID) {

        try {
            personaRepository.deleteById(personID);
        } catch (HttpMessageNotReadableException e) {
            System.out.println("Data Access");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.OK);

    }


    public ResponseEntity<String> updatePerson(Persona person) {

        System.out.println("The corresponding id is:" + person.getId());
        personaRepository.deleteById(person.getId());
        personaRepository.insert(person);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
