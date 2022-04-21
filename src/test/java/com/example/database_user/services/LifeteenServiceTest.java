package com.example.database_user.services;

import com.example.database_user.dtos.Lifeteen;
import com.example.database_user.dtos.Persona.Ninos.InscritoNinos;
import com.example.database_user.dtos.Persona.Persona;
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
    static String ltid = "6260506b77ffab0f071ad075";
    @Autowired
    private LifeteenService lifeteenService;
    @Autowired
    private PersonaService personaService;

    @BeforeAll
    static void beforeAll() {
        Sacraments sacraments = new Sacraments();
        persona = new Persona("2345sdgf", "Jose", "Martinez", "Rodri", "jose.martinez@gmail.com", LocalDate.now(), LocalDate.now(),
                "12121212D", null, sacraments, null, LocalDateTime.now());
        persona2 = new Persona("dfaa22235", "Alba", "Rodriguez", "Ro", "alba.rodriguez@gmail.com", LocalDate.now(), LocalDate.now(),
                "12121212D", null, sacraments, null, LocalDateTime.now());
        persona3 = new Persona("sdfgjksdfgjkq34", "Ign", "Nunez", "NNUNU", "ign.nunez@gmail.com", LocalDate.now(), LocalDate.now(),
                "12121212D", null, sacraments, null, LocalDateTime.now());
    }

    @Test
    @Order(1)
    void insertNewLifeteen() {

        Lifeteen lifeteen = new Lifeteen(ltid, persona.getId(), persona2.getId(), LocalDate.now(), null, Collections.singletonList(persona3.getId()));
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
        assertEquals(persona.getId(), lifeteenList.get(0).getResponsable1());
    }

    @Test
    @Order(3)
    void updateLifeteen() {

        Lifeteen lifeteen = new Lifeteen(ltid, persona.getId(), persona2.getId(), LocalDate.now(), Collections.singletonList("elmoni"), Collections.singletonList(persona3.getId()));
        ResponseEntity<String> back = lifeteenService.updateLifeteen(lifeteen);
        assertEquals(HttpStatus.OK, back.getStatusCode());

    }


    @Test
    @Order(4)
    void addNewUserNewInsciption() {
        InscritoNinos.InnerIncritoNinos innerIncritoNinos = new InscritoNinos.InnerIncritoNinos("padre", "madre", "de unos amigos", "123412", "eduedu@gmail.com", true, true, "Xaloc", "Bachi");
        InscritoNinos inscritoNinos = new InscritoNinos("john", "black", "perez", "john@black.es", LocalDate.now(), LocalDate.now(), "232323j", null, null, null, LocalDateTime.now(), innerIncritoNinos);
        ResponseEntity<String> back = lifeteenService.addNewUserNewInsciption(inscritoNinos, ltid);
        assertEquals("User added in the specified lifeteen id", back.getBody());

    }

//    @Test
//    @Order(6)
//    void deleteLifeteenById() {
//
//        ResponseEntity<String> back = lifeteenService.deleteLifeteenById(ltid);
//        assertEquals(HttpStatus.OK, back.getStatusCode());
//
//    }


    //    @Test
//    void addExistingUserExistingInscription() {
//        personaService.insertNewPerson(persona);
//
//        lifeteenService.addExistingUserExistingInscription()
//
//    }
//
//
    @Test
    @Order(5)
    void addExistingUserNewInscription() {
        InscritoNinos incritoNinos = new InscritoNinos(new InscritoNinos.InnerIncritoNinos("padre", "madre",
                "de unos amigos", "123412", "de@dw.com", true, true, "Xaxa", "bachi"));
        ResponseEntity<String> back = lifeteenService.addExistingUserNewInscription(persona.getId(), incritoNinos, ltid);
        assertEquals(HttpStatus.CREATED, back.getStatusCode());

    }
//
//
//    @Test
//    void editExistingUserInscription() {
//
//    }
//
//
//    @Test
//    void deleteExistingInscription() {
//
//    }

}