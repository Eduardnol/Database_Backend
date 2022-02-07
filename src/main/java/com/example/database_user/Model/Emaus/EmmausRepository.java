package com.example.database_user.Model.Emaus;

import com.example.database_user.Model.Persona.Persona;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmmausRepository extends MongoRepository<Persona, String> {
}
