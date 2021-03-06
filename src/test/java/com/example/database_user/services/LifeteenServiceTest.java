package com.example.database_user.services;

import com.example.database_user.dtos.Lifeteen;
import com.example.database_user.dtos.Persona.Persona;
import com.example.database_user.dtos.Persona.PersonaNinos;
import com.example.database_user.dtos.Persona.SimplePersona;
import com.example.database_user.dtos.Sacraments;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class LifeteenServiceTest {
    static Persona persona;
    static Persona persona2;
    static Persona persona3;
    static Persona persona4;
    static String ltid = "6260506b77ffab0f071ad075";
    @Autowired
    private LifeteenService lifeteenService;

    @Autowired
    private PersonaService personaService;

    @BeforeAll
    static void beforeAll() {
        Sacraments sacraments = new Sacraments();
        persona = new Persona("2345sdgf", "Jose", "Martinez", "Rodri", "jose.martinez@gmail.com", LocalDate.now(), LocalDate.now(),
                "12121212D", null, sacraments, null, LocalDateTime.now(), null, null);
        persona2 = new Persona("dfaa22235", "Alba", "Rodriguez", "Ro", "alba.rodriguez@gmail.com", LocalDate.now(), LocalDate.now(),
                "12121212D", null, sacraments, null, LocalDateTime.now(), null, null);
        persona3 = new Persona("sdfgjksdfgjkq34", "Ign", "Nunez", "NNUNU", "ign.nunez@gmail.com", LocalDate.now(), LocalDate.now(),
                "12121212D", null, sacraments, null, LocalDateTime.now(), null, null);
        persona4 = new Persona("elmoni", "Eduardo", "Nunez", "NNUNU", "eduardo.nunez@gmail.com", LocalDate.now(), LocalDate.now(),
                "12121212D", null, sacraments, null, LocalDateTime.now(), null, null);


    }

    @Test
    @Order(1)
    void insertNewLifeteen() {
        personaService.insertNewPerson(persona);
        personaService.insertNewPerson(persona2);
        personaService.insertNewPerson(persona3);
        personaService.insertNewPerson(persona4);

        Integer inscritos = lifeteenService.countInscritos(ltid);

        Lifeteen lifeteen = new Lifeteen(ltid, "Lifeteen 2020", new SimplePersona(persona.getId(), persona.getNombre(), persona.getApellido()),
                new SimplePersona(persona2.getId(), persona2.getNombre(), persona2.getApellido()),
                LocalDate.now(), inscritos, null, Collections.singletonList(new SimplePersona(persona3.getId(), persona3.getNombre(), persona3.getApellido())));
        ResponseEntity<String> back = lifeteenService.insertNewLifeteen(lifeteen);
        assertEquals(HttpStatus.CREATED, back.getStatusCode());
    }

    @Test
    @Order(2)
    void fetchAllLifeteen() {
        //given
        //when
        List<Lifeteen> lifeteenList = lifeteenService.fetchAllLifeteen().getBody();
        //then
        assertEquals(persona.getNombre() + " " + persona.getApellido(), lifeteenList.get(0).getResponsable1());
    }

    @Test
    @Order(3)
    void updateLifeteen() {
        Integer inscritos = lifeteenService.countInscritos(ltid);
        Lifeteen lifeteen = new Lifeteen(ltid, "Lifeteen 2020", new SimplePersona(persona.getId(), persona.getNombre(), persona.getApellido()),
                new SimplePersona(persona2.getId(), persona2.getNombre(), persona2.getApellido()), LocalDate.now(), inscritos,
                Collections.singletonList(new SimplePersona("elmoni", persona.getNombre(), persona.getApellido())), Collections.singletonList(new SimplePersona(persona3.getId(), persona3.getNombre(), persona3.getApellido())));
        ResponseEntity<String> back = lifeteenService.updateLifeteen(lifeteen);
        assertEquals(HttpStatus.OK, back.getStatusCode());

    }


    @Test
    @Order(4)
    void addNewUserNewInsciption() {
        PersonaNinos innerIncritoNinos = new PersonaNinos("padre", "madre", "de unos amigos", "123412", "eduedu@gmail.com", true, true, "Xaloc", "Bachi");
        Persona personaNinos = new Persona(null, "john", "black", "perez", "john@black.es", LocalDate.now(), LocalDate.now(),
                "232323j", null, null, null, LocalDateTime.now(), null, innerIncritoNinos);
        ResponseEntity<String> back = lifeteenService.addNewUserNewInsciption(personaNinos, ltid);
        assertEquals("User added in the specified lifeteen id", back.getBody());

    }

    @Test
    @Order(5)
    void addExistingUserNewInscription() {
        PersonaNinos incritoNinos = new PersonaNinos("padre", "madre",
                "de unos amigos", "123412", "de@dw.com", true, true, "Xaxa", "bachi");
        persona.setPersonaNinos(incritoNinos);
        ResponseEntity<String> back = lifeteenService.addExistingUserNewInscription(persona, ltid);
        assertEquals(HttpStatus.CREATED, back.getStatusCode());

    }

//    @Test
//    @Order(6)
//    void deleteExistingInscriptionFromALifeteen() {
//        ResponseEntity<String> back = lifeteenService.deleteExistingInscriptionFromALifeteen(ltid, persona.getId());
//        assertEquals("The user has been deleted from the specified lifeteen", back.getBody());
//    }

    @Test
    @Order(7)
    void addExistingUserExistingInscription() {

        ResponseEntity<String> back = lifeteenService.addExistingUserExistingInscription(ltid, persona.getId());
        assertEquals("User added in the specified lifeteen id", back.getBody());

    }

    @Test
    @Order(8)
    void editExistingUserInscription() {
        PersonaNinos incritoNinos = new PersonaNinos("padre", "madre",
                "de unos amigos", "123412", "de@dw.com", true, true, "Xaloc", "bachi");
        persona.setPersonaNinos(incritoNinos);
        ResponseEntity<String> back = lifeteenService.editExistingUserInscription(persona);

        assertEquals("Inscription updated", back.getBody());

    }

//    @Test
//    @Order(9)
//    void deleteLifeteenById() {
//
//        ResponseEntity<String> back = lifeteenService.deleteLifeteenById(ltid);
//        assertEquals(HttpStatus.OK, back.getStatusCode());
//
//    }


}