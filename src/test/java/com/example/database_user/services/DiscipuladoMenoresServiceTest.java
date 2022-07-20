package com.example.database_user.services;

import com.example.database_user.dtos.DiscipuladoMenores;
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
    void insertNewDiscipuladoMenores() {
        personaService.insertNewPerson(persona);
        personaService.insertNewPerson(persona2);
        personaService.insertNewPerson(persona3);
        personaService.insertNewPerson(persona4);

        Integer inscritos = discipuladoMenoresService.countInscritos(ltid);

        DiscipuladoMenores discipuladoMenores = new DiscipuladoMenores(ltid, "Lifeteen 2020", new SimplePersona(persona.getId(), persona.getNombre(), persona.getApellido()),
                new SimplePersona(persona2.getId(), persona2.getNombre(), persona2.getApellido()),
                LocalDate.now(), inscritos, null, Collections.singletonList(new SimplePersona(persona3.getId(), persona3.getNombre(), persona3.getApellido())));
        ResponseEntity<String> back = discipuladoMenoresService.insertNewDiscipuladoMenores(discipuladoMenores);
        assertEquals(HttpStatus.CREATED, back.getStatusCode());
    }

    @Test
    @Order(2)
    void fetchAllDiscipuladoMenores() {
        //given
        //when
        List<DiscipuladoMenores> discipuladoMenoresList = discipuladoMenoresService.fetchAllDiscipuladoMenores().getBody();
        //then
        assertEquals(persona.getNombre() + " " + persona.getApellido(), discipuladoMenoresList.get(0).getResponsable1());
    }

    @Test
    @Order(3)
    void updateDiscipuladoMenores() {
        Integer inscritos = discipuladoMenoresService.countInscritos(ltid);
        DiscipuladoMenores discipuladoMenores = new DiscipuladoMenores(ltid, "DiscipuladoMenores 2020", new SimplePersona(persona.getId(), persona.getNombre(), persona.getApellido()),
                new SimplePersona(persona2.getId(), persona2.getNombre(), persona2.getApellido()), LocalDate.now(), inscritos,
                Collections.singletonList(new SimplePersona("elmoni", persona.getNombre(), persona.getApellido())), Collections.singletonList(new SimplePersona(persona3.getId(), persona3.getNombre(), persona3.getApellido())));
        ResponseEntity<String> back = discipuladoMenoresService.updateDiscipuladoMenores(discipuladoMenores);
        assertEquals(HttpStatus.OK, back.getStatusCode());

    }


    @Test
    @Order(4)
    void addNewUserNewInsciption() {
        PersonaNinos innerIncritoNinos = new PersonaNinos("padre", "madre", "de unos amigos", "123412", "eduedu@gmail.com", true, true, "Xaloc", "Bachi");
        Persona personaNinos = new Persona(null, "john", "black", "perez", "john@black.es", LocalDate.now(), LocalDate.now(),
                "232323j", null, null, null, LocalDateTime.now(), null, innerIncritoNinos);
        ResponseEntity<String> back = discipuladoMenoresService.addNewUserNewInsciption(personaNinos, ltid);
        assertEquals("User added in the specified discipuladoMenores id", back.getBody());

    }

    @Test
    @Order(5)
    void addExistingUserNewInscription() {
        PersonaNinos incritoNinos = new PersonaNinos("padre", "madre",
                "de unos amigos", "123412", "de@dw.com", true, true, "Xaxa", "bachi");
        persona.setPersonaNinos(incritoNinos);
        ResponseEntity<String> back = discipuladoMenoresService.addExistingUserNewInscription(persona, ltid);
        assertEquals(HttpStatus.CREATED, back.getStatusCode());

    }

//    @Test
//    @Order(6)
//    void deleteExistingInscriptionFromAdiscipuladoMenores() {
//        ResponseEntity<String> back = discipuladoMenoresService.deleteExistingInscriptionFromAdiscipuladoMenores(ltid, persona.getId());
//        assertEquals("The user has been deleted from the specified discipuladoMenores", back.getBody());
//    }

    @Test
    @Order(7)
    void addExistingUserExistingInscription() {

        ResponseEntity<String> back = discipuladoMenoresService.addExistingUserExistingInscription(ltid, persona.getId());
        assertEquals("User added in the specified discipuladoMenores id", back.getBody());

    }

    @Test
    @Order(8)
    void editExistingUserInscription() {
        PersonaNinos incritoNinos = new PersonaNinos("padre", "madre",
                "de unos amigos", "123412", "de@dw.com", true, true, "Xaloc", "bachi");
        persona.setPersonaNinos(incritoNinos);
        ResponseEntity<String> back = discipuladoMenoresService.editExistingUserInscription(persona);

        assertEquals("Inscription updated", back.getBody());

    }

//    @Test
//    @Order(9)
//    void deleteDiscipuladoMenoresById() {
//
//        ResponseEntity<String> back = DiscipuladoMenoresService.deleteDiscipuladoMenoresById(ltid);
//        assertEquals(HttpStatus.OK, back.getStatusCode());
//
//    }


}