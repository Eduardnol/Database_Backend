package com.example.database_user.Model.Persona;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface PersonaRepository extends MongoRepository<Persona, String> {
    Optional<Persona> findPersonaByBirthdayIsBetween(LocalDate start, LocalDate end);
}
