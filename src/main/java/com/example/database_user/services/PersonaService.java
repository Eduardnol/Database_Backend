package com.example.database_user.services;

import com.example.database_user.dtos.Persona.Persona;
import com.example.database_user.repositories.PersonaRepository;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.core.query.TextQuery;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class PersonaService {

  private static final Logger logger = LogManager.getLogger(PersonaService.class);
  private final PersonaRepository personaRepository;
  private final MongoTemplate mongoTemplate;


  public List<Persona> fetchAllPeople() {

    return personaRepository.findAll();

  }

  //Find a person by id
  public ResponseEntity<Persona> findPersonById(String id) {
    Persona persona = personaRepository.findById(id).orElse(null);
    if (persona == null) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(persona, HttpStatus.OK);
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
    HttpStatus status;

    if (endDate.isBefore(startDate)) {
      //Error
      status = HttpStatus.BAD_REQUEST;
      logger.warn("End date is before start date");
    } else {
      personaRepository.findAllByBirthdayBetween(startDate.minusDays(1)
          , endDate.plusDays(1)).ifPresent(queryResult::addAll);
      status = HttpStatus.OK;
      logger.info("Retrieved users by datarange");
    }
    return new ResponseEntity<>(queryResult, status);

  }


  public ResponseEntity<String> insertNewPerson(Persona person) {

    logger.info("Inserting new person");
    personaRepository.insert(person);

    MeilisearchService meilisearchService = MeilisearchService.getInstance();
    meilisearchService.addUserDocument(person);

    return new ResponseEntity<>(HttpStatus.CREATED);

  }


  public ResponseEntity<String> deletePerson(String personID) {

    try {
      personaRepository.deleteById(personID);
      MeilisearchService meilisearchService = MeilisearchService.getInstance();
      meilisearchService.deleteDocument(personID);
    } catch (HttpMessageNotReadableException e) {
      System.out.println("Data Access");
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    return new ResponseEntity<>(HttpStatus.OK);

  }

  //Todo this method does not work well when having other fields in the same document that are not in the same class
  public ResponseEntity<String> updatePerson(Persona person) {

    personaRepository.save(person);
    MeilisearchService meilisearchService = MeilisearchService.getInstance();
    meilisearchService.updateDocument(person);
    return new ResponseEntity<>(HttpStatus.OK);
  }


  /**
   * Sorts people by name
   *
   * @return
   */
  public ResponseEntity<List<Persona>> sortPeopleByName(String sort) {

    Sort.Direction sort_dir;
    if (sort.equals("asc")) {
      sort_dir = Sort.Direction.ASC;
    } else {
      sort_dir = Sort.Direction.DESC;
    }
    HttpStatus status = HttpStatus.ACCEPTED;
    List<Persona> persons = personaRepository.findAll(Sort.by(sort_dir, "nombre"));
    status = HttpStatus.OK;
    return new ResponseEntity<List<Persona>>(persons, status);
  }


  /**
   * Sorts people by name
   *
   * @return
   */
  public ResponseEntity<List<Persona>> sortPeopleBySurname(String sort) {

    Sort.Direction sort_dir;
    if (sort.equals("asc")) {
      sort_dir = Sort.Direction.ASC;
    } else {
      sort_dir = Sort.Direction.DESC;
    }

    HttpStatus status = HttpStatus.ACCEPTED;
    List<Persona> persons = personaRepository.findAll(Sort.by(sort_dir, "apellido"));
    status = HttpStatus.OK;
    return new ResponseEntity<List<Persona>>(persons, status);
  }
}
