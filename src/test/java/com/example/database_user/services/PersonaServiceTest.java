package com.example.database_user.services;

import com.example.database_user.dtos.Persona.Persona;
import com.example.database_user.dtos.Sacraments;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class PersonaServiceTest {
    @Autowired
    private PersonaService personaService;

    @Test
    void fetchAllPeople() {
        assertNotEquals((long) personaService.fetchAllPeople().size(), 0);
    }

    @Test
    void findPersonById() {

        Sacraments sacraments = new Sacraments();
        Persona persona = personaService.findPersonById("2345sdgf").getBody();
        assertEquals(persona, new Persona("2345sdgf", "Jose", "Martinez", "Rodri", "jose.martinez@gmail.com", LocalDate.now(), LocalDate.now(),
                "12121212D", null, sacraments, null, LocalDateTime.now(), null, null));


    }

    @Test
    void fetchPeopleByName() {
        Sacraments sacraments = new Sacraments();
        assertTrue(personaService.fetchPeopleByName("Jose").getBody().
                contains(new Persona("2345sdgf", "Jose", "Martinez", "Rodri", "jose.martinez@gmail.com", LocalDate.now(), LocalDate.now(),
                        "12121212D", null, sacraments, null, LocalDateTime.now(), null, null)));

    }

    @Test
    void fetchBirthRangePeople() {
    }

    @Test
    void insertNewPerson() {
    }

    @Test
    void deletePerson() {
    }

    @Test
    void updatePerson() {
    }

    @Test
    void sortPeopleByName() {
    }

    @Test
    void sortPeopleBySurname() {
    }
}