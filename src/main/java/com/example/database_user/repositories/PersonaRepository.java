package com.example.database_user.repositories;

import com.example.database_user.dtos.Persona;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PersonaRepository extends MongoRepository<Persona, String> {
    Optional<List<Persona>> findAllByBirthdayBetween(LocalDate start, LocalDate end);
}