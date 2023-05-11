package com.example.database_user.repositories;

import com.example.database_user.dtos.Persona.Persona;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmmausRepository extends MongoRepository<Persona, String> {

}
