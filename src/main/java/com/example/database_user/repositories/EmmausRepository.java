package com.example.database_user.repositories;

import com.example.database_user.controllers.dto.Persona.PersonaDTO;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmmausRepository extends MongoRepository<PersonaDTO, String> {

}
