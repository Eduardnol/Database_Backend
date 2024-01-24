package com.example.database_user.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.database_user.controllers.dto.DiscipuladoMenores;
import com.example.database_user.controllers.dto.Persona.PersonaDTO;
import com.example.database_user.controllers.dto.Persona.PersonaNinos;
import com.example.database_user.controllers.dto.Persona.PersonaSacraments;
import com.example.database_user.controllers.dto.Persona.SimplePersona;
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

  static PersonaDTO personaDTO;
  static PersonaDTO personaDTO2;
  static PersonaDTO personaDTO3;
  static PersonaDTO personaDTO4;
  static String ltid = "6260506b77ffab0f071ad075";
  @Autowired
  private DiscipuladoMenoresService discipuladoMenoresService;

  @Autowired
  private PersonaServiceImplementation personaServiceImplementation;

  @BeforeAll
  static void beforeAll() {
    List<PersonaSacraments> sacraments = new ArrayList<PersonaSacraments>();
    personaDTO = PersonaDTO.builder().id("2345sdgf").nombre("Jose").apellido("Martinez")
        .apellido2("Rodri").email("jose.martinez@gmail.com").birthday(LocalDate.now())
        .saint(LocalDate.now()).dni("12121212D").extras(null).sacraments(sacraments)
        .fileStorage(null).createdOn(LocalDateTime.now()).personGroups(null).personaNinos(null)
        .build();

    personaDTO2 = PersonaDTO.builder().id("dfaa22235").nombre("Alba").apellido("Rodriguez")
        .apellido2("Rodri").email("alba.rodriguez@gmail.com").birthday(LocalDate.now())
        .saint(LocalDate.now()).dni("12121212D").extras(null).sacraments(sacraments)
        .fileStorage(null).createdOn(LocalDateTime.now()).personGroups(null).personaNinos(null)
        .build();

    personaDTO3 = PersonaDTO.builder().id("2342222225sdgf").nombre("Jo2222se").apellido("Martinez")
        .apellido2("Rodri").email("jose.martinez@gmail.com").birthday(LocalDate.now())
        .saint(LocalDate.now()).dni("12121212D").extras(null).sacraments(sacraments)
        .fileStorage(null).createdOn(LocalDateTime.now()).personGroups(null).personaNinos(null)
        .build();

    personaDTO4 = PersonaDTO.builder().id("elmoni").nombre("Edu").apellido("Moni")
        .apellido2("Rodri")
        .email("edu@gmail.com").birthday(LocalDate.now()).saint(LocalDate.now()).dni("12121212D")
        .extras(null).sacraments(sacraments).fileStorage(null).createdOn(LocalDateTime.now())
        .personGroups(null).personaNinos(null).build();


  }

  @Test
  @Order(1)
  void insertNewDiscipuladoMenores() {
    personaServiceImplementation.insertNewPerson(personaDTO);
    personaServiceImplementation.insertNewPerson(personaDTO2);
    personaServiceImplementation.insertNewPerson(personaDTO3);
    personaServiceImplementation.insertNewPerson(personaDTO4);

    Integer inscritos = discipuladoMenoresService.countInscritos(ltid);

    DiscipuladoMenores discipuladoMenores = new DiscipuladoMenores(ltid, "Lifeteen 2020",
        new ArrayList<SimplePersona>(Arrays.asList(
            new SimplePersona(personaDTO.getId(), personaDTO.getNombre(), personaDTO.getApellido()),
            new SimplePersona(personaDTO2.getId(), personaDTO2.getNombre(),
                personaDTO2.getApellido()))),
        LocalDate.now(), inscritos, null, Collections.singletonList(
        new SimplePersona(personaDTO3.getId(), personaDTO3.getNombre(), personaDTO3.getApellido())),
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
    assertEquals(personaDTO.getNombre(),
        discipuladoMenoresList.get(0).getResponsables().get(0).getNombre());
  }

  @Test
  @Order(3)
  void updateDiscipuladoMenores() {
    Integer inscritos = discipuladoMenoresService.countInscritos(ltid);
    DiscipuladoMenores discipuladoMenores = new DiscipuladoMenores(ltid, "DiscipuladoMenores 2020",
        Arrays.asList(
            new SimplePersona(personaDTO.getId(), personaDTO.getNombre(), personaDTO.getApellido()),
            new SimplePersona(personaDTO2.getId(), personaDTO2.getNombre(),
                personaDTO2.getApellido())),
        LocalDate.now(), inscritos, Collections.singletonList(
        new SimplePersona("elmoni", personaDTO.getNombre(), personaDTO.getApellido())),
        Collections.singletonList(
            new SimplePersona(personaDTO3.getId(), personaDTO3.getNombre(),
                personaDTO3.getApellido())),
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
    PersonaDTO personaDTONinos = PersonaDTO.builder().id(null).nombre("John").apellido("Black")
        .apellido2("Rodri").email("jose.martinez@gmail.com").birthday(LocalDate.now())
        .saint(LocalDate.now()).dni("12121212D").extras(null).sacraments(null).fileStorage(null)
        .createdOn(LocalDateTime.now()).personGroups(null).personaNinos(null).build();
    ResponseEntity<String> back = discipuladoMenoresService.addNewUserNewInsciption(personaDTONinos,
        ltid);
    assertEquals("User added in the specified discipuladoMenores id", back.getBody());

  }

  @Test
  @Order(5)
  void addExistingUserNewInscription() {
    PersonaNinos incritoNinos = new PersonaNinos("padre", "madre", "de unos amigos", "123412",
        "de@dw.com", true, true, "Xaxa", "bachi");
    personaDTO.setPersonaNinos(incritoNinos);
    ResponseEntity<String> back = discipuladoMenoresService.addExistingUserNewInscription(
        personaDTO,
        ltid);
    assertEquals(HttpStatus.CREATED, back.getStatusCode());

  }

  @Test
  @Order(6)
  void deleteExistingInscriptionFromAdiscipuladoMenores() {
    ResponseEntity<String> back = discipuladoMenoresService.deleteExistingInscriptionFromADiscipuladoMenores(
        ltid, personaDTO.getId());
    assertEquals("The user has been deleted from the specified discipuladoMenores", back.getBody());
  }

  @Test
  @Order(7)
  void addExistingUserExistingInscription() {

    ResponseEntity<String> back = discipuladoMenoresService.addExistingUserExistingInscription(ltid,
        personaDTO.getId());
    assertEquals("User added in the specified discipuladoMenores id", back.getBody());

  }

  @Test
  @Order(8)
  void editExistingUserInscription() {
    PersonaNinos incritoNinos = new PersonaNinos("padre", "madre", "de unos amigos", "123412",
        "de@dw.com", true, true, "Xaloc", "bachi");
    personaDTO.setPersonaNinos(incritoNinos);
    ResponseEntity<String> back = discipuladoMenoresService.editExistingUserInscription(personaDTO);

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
    personaServiceImplementation.deletePersonById(personaDTO.getId());
    personaServiceImplementation.deletePersonById(personaDTO2.getId());
    personaServiceImplementation.deletePersonById(personaDTO3.getId());
    personaServiceImplementation.deletePersonById(personaDTO4.getId());
  }


}
