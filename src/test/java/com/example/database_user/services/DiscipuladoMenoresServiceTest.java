package com.example.database_user.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.database_user.dtos.DiscipuladoMenores;
import com.example.database_user.dtos.Persona.Persona;
import com.example.database_user.dtos.Persona.PersonaNinos;
import com.example.database_user.dtos.Persona.PersonaSacraments;
import com.example.database_user.dtos.Persona.SimplePersona;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DiscipuladoMenoresServiceTest {

  static Persona persona;
  static Persona persona2;
  static Persona persona3;
  static Persona persona4;
  static String ltid = "6260506b77ffab0f071ad075";
  @Autowired
  private DiscipuladoMenoresService discipuladoMenoresService;

  @Autowired
  private PersonaService personaService;

  @BeforeAll
  static void beforeAll() {
    List<PersonaSacraments> sacraments = new ArrayList<PersonaSacraments>();
    persona = Persona.builder().id("2345sdgf").nombre("Jose").apellido("Martinez")
        .apellido2("Rodri").email("jose.martinez@gmail.com").birthday(LocalDate.now())
        .saint(LocalDate.now()).dni("12121212D").extras(null).sacraments(sacraments)
        .fileStorage(null).createdOn(LocalDateTime.now()).personGroups(null).personaNinos(null)
        .build();

    persona2 = Persona.builder().id("dfaa22235").nombre("Alba").apellido("Rodriguez")
        .apellido2("Rodri").email("alba.rodriguez@gmail.com").birthday(LocalDate.now())
        .saint(LocalDate.now()).dni("12121212D").extras(null).sacraments(sacraments)
        .fileStorage(null).createdOn(LocalDateTime.now()).personGroups(null).personaNinos(null)
        .build();

    persona3 = Persona.builder().id("2342222225sdgf").nombre("Jo2222se").apellido("Martinez")
        .apellido2("Rodri").email("jose.martinez@gmail.com").birthday(LocalDate.now())
        .saint(LocalDate.now()).dni("12121212D").extras(null).sacraments(sacraments)
        .fileStorage(null).createdOn(LocalDateTime.now()).personGroups(null).personaNinos(null)
        .build();

    persona4 = Persona.builder().id("elmoni").nombre("Edu").apellido("Moni").apellido2("Rodri")
        .email("edu@gmail.com").birthday(LocalDate.now()).saint(LocalDate.now()).dni("12121212D")
        .extras(null).sacraments(sacraments).fileStorage(null).createdOn(LocalDateTime.now())
        .personGroups(null).personaNinos(null).build();


  }

  @Test
  @Order(1)
  void insertNewDiscipuladoMenores() {
    personaService.insertNewPerson(persona);
    personaService.insertNewPerson(persona2);
    personaService.insertNewPerson(persona3);
    personaService.insertNewPerson(persona4);

    Integer inscritos = discipuladoMenoresService.countInscritos(ltid);

    DiscipuladoMenores discipuladoMenores = new DiscipuladoMenores(ltid, "Lifeteen 2020",
        new ArrayList<SimplePersona>(Arrays.asList(
            new SimplePersona(persona.getId(), persona.getNombre(), persona.getApellido()),
            new SimplePersona(persona2.getId(), persona2.getNombre(), persona2.getApellido()))),
        LocalDate.now(), inscritos, null, Collections.singletonList(
        new SimplePersona(persona3.getId(), persona3.getNombre(), persona3.getApellido())),
        Collections.singletonList(null));

    ResponseEntity<String> back = discipuladoMenoresService.insertNewDiscipuladoMenores(
        discipuladoMenores);

    assertEquals(HttpStatus.CREATED, back.getStatusCode());
  }

  @Test
  @Order(2)
  void fetchAllDiscipuladoMenores() {
    //given
    //when
    List<DiscipuladoMenores> discipuladoMenoresList = discipuladoMenoresService.fetchAllDiscipuladoMenores()
        .getBody();
    //then
    assertEquals(persona.getNombre(),
        discipuladoMenoresList.get(0).getResponsables().get(0).getNombre());
  }

  @Test
  @Order(3)
  void updateDiscipuladoMenores() {
    Integer inscritos = discipuladoMenoresService.countInscritos(ltid);
    DiscipuladoMenores discipuladoMenores = new DiscipuladoMenores(ltid, "DiscipuladoMenores 2020",
        Arrays.asList(
            new SimplePersona(persona.getId(), persona.getNombre(), persona.getApellido()),
            new SimplePersona(persona2.getId(), persona2.getNombre(), persona2.getApellido())),
        LocalDate.now(), inscritos, Collections.singletonList(
        new SimplePersona("elmoni", persona.getNombre(), persona.getApellido())),
        Collections.singletonList(
            new SimplePersona(persona3.getId(), persona3.getNombre(), persona3.getApellido())),
        Collections.singletonList(null));

    ResponseEntity<String> back = discipuladoMenoresService.updateDiscipuladoMenores(
        discipuladoMenores);
    assertEquals(HttpStatus.OK, back.getStatusCode());

  }


  @Test
  @Order(4)
  void addNewUserNewInsciption() {
    PersonaNinos innerIncritoNinos = new PersonaNinos("padre", "madre", "de unos amigos", "123412",
        "eduedu@gmail.com", true, true, "Xaloc", "Bachi");
    Persona personaNinos = Persona.builder().id(null).nombre("John").apellido("Black")
        .apellido2("Rodri").email("jose.martinez@gmail.com").birthday(LocalDate.now())
        .saint(LocalDate.now()).dni("12121212D").extras(null).sacraments(null).fileStorage(null)
        .createdOn(LocalDateTime.now()).personGroups(null).personaNinos(null).build();
    ResponseEntity<String> back = discipuladoMenoresService.addNewUserNewInsciption(personaNinos,
        ltid);
    assertEquals("User added in the specified discipuladoMenores id", back.getBody());

  }

  @Test
  @Order(5)
  void addExistingUserNewInscription() {
    PersonaNinos incritoNinos = new PersonaNinos("padre", "madre", "de unos amigos", "123412",
        "de@dw.com", true, true, "Xaxa", "bachi");
    persona.setPersonaNinos(incritoNinos);
    ResponseEntity<String> back = discipuladoMenoresService.addExistingUserNewInscription(persona,
        ltid);
    assertEquals(HttpStatus.CREATED, back.getStatusCode());

  }

  @Test
  @Order(6)
  void deleteExistingInscriptionFromAdiscipuladoMenores() {
    ResponseEntity<String> back = discipuladoMenoresService.deleteExistingInscriptionFromADiscipuladoMenores(
        ltid, persona.getId());
    assertEquals("The user has been deleted from the specified discipuladoMenores", back.getBody());
  }

  @Test
  @Order(7)
  void addExistingUserExistingInscription() {

    ResponseEntity<String> back = discipuladoMenoresService.addExistingUserExistingInscription(ltid,
        persona.getId());
    assertEquals("User added in the specified discipuladoMenores id", back.getBody());

  }

  @Test
  @Order(8)
  void editExistingUserInscription() {
    PersonaNinos incritoNinos = new PersonaNinos("padre", "madre", "de unos amigos", "123412",
        "de@dw.com", true, true, "Xaloc", "bachi");
    persona.setPersonaNinos(incritoNinos);
    ResponseEntity<String> back = discipuladoMenoresService.editExistingUserInscription(persona);

    assertEquals("Inscription updated", back.getBody());

  }

  @Test
  @Order(9)
  void deleteDiscipuladoMenoresById() {

    ResponseEntity<String> back = discipuladoMenoresService.deleteDiscipuladoMenoresById(ltid);
    assertEquals(HttpStatus.OK, back.getStatusCode());

  }

  @Test
  @Order(10)
  void deleteUsersFromDatabase() {
    personaService.deletePersonById(persona.getId());
    personaService.deletePersonById(persona2.getId());
    personaService.deletePersonById(persona3.getId());
    personaService.deletePersonById(persona4.getId());
  }


}