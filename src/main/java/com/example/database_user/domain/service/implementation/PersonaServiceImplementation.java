package com.example.database_user.domain.service.implementation;

import com.example.database_user.controllers.dto.Persona.PersonaDTO;
import com.example.database_user.domain.model.mapper.PersonaMapper;
import com.example.database_user.domain.service.PersonaService;
import com.example.database_user.repositories.PersonaRepository;
import com.example.database_user.repositories.entity.PersonaEntity;
import com.example.database_user.services.MeilisearchService;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Log4j2
@Transactional
public class PersonaServiceImplementation implements PersonaService {

  @Autowired
  private PersonaRepository personaRepository;
  @Autowired
  private PersonaMapper personaMapper;

  @Override
  public List<PersonaDTO> fetchAllPeople(Integer page, Integer size) {

    Pageable pageable = PageRequest.of(page, size);
    return personaRepository.findAll(pageable).stream().map(personaMapper::toDTO)
        .collect(Collectors.toList());

  }

  @Override
  public ResponseEntity<PersonaDTO> findPersonById(String id) {
    PersonaDTO personaDTO = personaRepository.findById(id).map(personaMapper::toDTO).orElse(null);
    if (personaDTO == null) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(personaDTO, HttpStatus.OK);
  }

  @Override
  public ResponseEntity<List<PersonaDTO>> fetchPeopleByName(String name) {

    HttpStatus status = HttpStatus.ACCEPTED;
    TextCriteria criteria = TextCriteria
        .forDefaultLanguage()
        .matching(name);

    // Query query = TextQuery.queryText(criteria).sortByScore();
    // List<PersonaDTO> posts = mongoTemplate.find(query, PersonaDTO.class);

    List<PersonaDTO> posts = personaRepository
        .findAllByNombre(name)
        .stream()
        .map(personaMapper::toDTO)
        .collect(Collectors.toList());
    status = HttpStatus.OK;
    log.info("Retrieved users by name");

    return new ResponseEntity<List<PersonaDTO>>(posts, status);

  }

  @Override
  public ResponseEntity<List<PersonaDTO>> fetchBirthRangePeople(String start, String end) {

    DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;
    List<PersonaDTO> queryResult = new ArrayList<>();
    LocalDate startDate = LocalDate.parse(start, formatter);
    LocalDate endDate = LocalDate.parse(end, formatter);
    HttpStatus status;

    if (endDate.isBefore(startDate)) {
      //Error
      status = HttpStatus.BAD_REQUEST;
      log.warn("End date is before start date");
    } else {
     /* personaRepository.findAllByBirthdayBetween(startDate.minusDays(1)
          , endDate.plusDays(1)).ifPresent(queryResult::addAll);*/
      status = HttpStatus.OK;
      log.info("Retrieved users by datarange");
    }
    return new ResponseEntity<>(queryResult, status);

  }

  @Override
  public ResponseEntity<String> insertNewPerson(PersonaDTO person) {

    log.info("Inserting new person");
    System.out.println(person);

    try {
      PersonaEntity savedPerson = personaRepository.insert(personaMapper.toEntity(person));
      log.info("Person inserted");
      MeilisearchService meilisearchService = MeilisearchService.getInstance();
      meilisearchService.addUserDocument(person);
      return new ResponseEntity<>("Person inserted with id:" + savedPerson,
          HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

  }

  @Override
  public ResponseEntity<String> deletePersonById(String personID) {

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
  @Override
  public ResponseEntity<String> updatePerson(PersonaDTO person) {

    personaRepository.save(personaMapper.toEntity(person));
    MeilisearchService meilisearchService = MeilisearchService.getInstance();
    meilisearchService.updateDocument(person);
    return new ResponseEntity<>(HttpStatus.OK);
  }


  /**
   * Sorts people by name
   *
   * @return
   */
  @Override
  public ResponseEntity<List<PersonaDTO>> sortPeopleByName(String sort) {

    Sort.Direction sort_dir;
    if (sort.equals("asc")) {
      sort_dir = Sort.Direction.ASC;
    } else {
      sort_dir = Sort.Direction.DESC;
    }
    HttpStatus status = HttpStatus.ACCEPTED;
    List<PersonaDTO> persons = personaRepository.findAll(Sort.by(sort_dir, "nombre")).stream()
        .map(personaMapper::toDTO).collect(Collectors.toList());
    status = HttpStatus.OK;
    return new ResponseEntity<List<PersonaDTO>>(persons, status);
  }


  /**
   * Sorts people by name
   *
   * @return
   */
  @Override
  public ResponseEntity<List<PersonaDTO>> sortPeopleBySurname(String sort) {

    Sort.Direction sort_dir;
    if (sort.equals("asc")) {
      sort_dir = Sort.Direction.ASC;
    } else {
      sort_dir = Sort.Direction.DESC;
    }

    HttpStatus status = HttpStatus.ACCEPTED;
    List<PersonaDTO> persons = personaRepository.findAll(Sort.by(sort_dir, "apellido"))
        .stream()
        .map(personaMapper::toDTO)
        .collect(Collectors.toList());
    status = HttpStatus.OK;
    return new ResponseEntity<List<PersonaDTO>>(persons, status);
  }
}
