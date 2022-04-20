package com.example.database_user.services;

import com.example.database_user.dtos.Lifeteen;
import com.example.database_user.dtos.Persona.Persona;
import com.example.database_user.dtos.Sacraments;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
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
class LifeteenServiceTest {
    static Persona persona;
    static Persona persona2;
    static Persona persona3;
    static String ltid = "asldkjflkajsdf";
    @Autowired
    private LifeteenService lifeteenService;

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
        Lifeteen lifeteen = new Lifeteen(persona.getId(), persona2.getId(), LocalDate.now(), null, Collections.singletonList(persona3.getId()));
        ResponseEntity<String> back = lifeteenService.insertNewLifeteen(lifeteen);
        assertEquals(HttpStatus.CREATED, back.getStatusCode());
    }

    @Test
    @Order(2)
    void fetchAllLifeteen() {
        //given
        Lifeteen lifeteen = new Lifeteen(ltid, persona.getId(), persona2.getId(), LocalDate.now(), null, Collections.singletonList(persona3.getId()));

        //when
        List<Lifeteen> lifeteenList = lifeteenService.fetchAllLifeteen().getBody();
        //then
        assertEquals(persona.getId(), lifeteenList.get(0).getResponsable1());
    }

    @Test
    @Order(3)
    void updateLifeteen() {

    }


    @Test
    @Order(4)
    void deleteLifeteenById() {

        ResponseEntity<String> back = lifeteenService.deleteLifeteenById(ltid);
        assertEquals(HttpStatus.OK, back.getStatusCode());

    }


    @Test
    void addNewUserNewInsciption() {


    }


    @Test
    void addExistingUserExistingInscription() {

    }


    @Test
    void addExistingUserNewInscription() {

    }


    @Test
    void editExistingUserInscription() {

    }


    @Test
    void deleteExistingInscription() {

    }
}