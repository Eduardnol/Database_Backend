package com.example.database_user.services;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import com.example.database_user.dtos.Persona.Persona;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PersonaServiceTest {

  @Autowired
  private PersonaService personaService;

  @Test
  void fetchAllPeople() {
    assertNotEquals((long) personaService.fetchAllPeople(0, 10).size(), 0);
  }

  @Test
  void findPersonById() {

    Sacraments sacraments = new Sacraments();
    Persona persona = personaService.findPersonById("2345sdgf").getBody();
    //assertEquals(persona, new PersonaBuilder().addId("2345sdgf").addNombre("Jose").addApellido("Martinez").addApellido2("Rodri").addEmail("jose.martinez@gmail.com").addBirthday(LocalDate.now()).addSaint(LocalDate.now()).addDni("12121212D").addExtras(null).addSacraments(sacraments).addFileStorage(null).addCreatedOn(LocalDateTime.now()).addPersonGroups(null).addPersonaNinos(null).createPersona());

  }

  @Test
  void fetchPeopleByName() {
    Sacraments sacraments = new Sacraments();
    //assertTrue(personaService.fetchPeopleByName("Jose").getBody().
    //    contains(new PersonaBuilder().addId("2345sdgf").addNombre("Jose").addApellido("Martinez").addApellido2("Rodri").addEmail("jose.martinez@gmail.com").addBirthday(LocalDate.now()).addSaint(LocalDate.now()).addDni("12121212D").addExtras(null).addSacraments(sacraments).addFileStorage(null).addCreatedOn(LocalDateTime.now()).addPersonGroups(null).addPersonaNinos(null).createPersona()));

  }

//  @Test
//  void fetchBirthRangePeople() {
//  }
//
//  @Test
//  void insertNewPerson() {
//  }
//
//  @Test
//  void deletePerson() {
//  }
//
//  @Test
//  void updatePerson() {
//  }
//
//  @Test
//  void sortPeopleByName() {
//  }
//
//  @Test
//  void sortPeopleBySurname() {
//  }
}