package com.example.database_user.repositories;

import com.example.database_user.dtos.Persona;
import com.example.database_user.dtos.Stats;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StatisticsRepository extends MongoRepository<Stats, String> {
}
