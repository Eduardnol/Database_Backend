package com.example.database_user.domain.service.implementation;

import com.example.database_user.controllers.dto.Persona.PersonaDTO;
import com.example.database_user.repositories.PersonaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class StatisticsServiceImplementation implements
    com.example.database_user.domain.service.StatisticsService {

  @Autowired
  private PersonaRepository personaRepository;
  @Autowired
  private MongoOperations mongoOperations;


    /*@Aggregation(pipeline = {"""
            $match: {
                  $expr: {
                    $and: [
                      { $eq: [{ $dayOfMonth: '$birthDate' }, { $dayOfMonth: new Date() }] },
                      { $eq: [{ $month: '$birthDate' }, { $month: new Date() }] },
                    ],
                  },
                }"""})
    List<PersonaDTO> getTodayBirthday() {
        return null;
    }*/


  /**
   * Calculates all the people inside the database
   *
   * @return Integer with value of all users in database
   */
  public Integer countAllPeople() {

    return Math.toIntExact(personaRepository.count());

  }


  //TODO: Implement this method
  public ResponseEntity<List<PersonaDTO>> getTodayBirthdays() {

    /*	DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;
        List<PersonaDTO> queryResult = new ArrayList<>();
        HttpStatus status;

        Aggregation aggregation = Aggregation.newAggregation();*/
        /*personaRepository.findAllByBirthdayBetween(LocalDate.now().plusDays(1),
                LocalDate.now().plusDays(1)).ifPresent(queryResult::addAll);*/

    HttpStatus status = HttpStatus.OK;

    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    //return new ResponseEntity<>(personaRepository.findCustomByRegExDomain(), status);
  }
}
