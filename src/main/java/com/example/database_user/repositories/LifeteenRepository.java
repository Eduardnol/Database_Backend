package com.example.database_user.repositories;

import com.example.database_user.dtos.Lifeteen;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface LifeteenRepository extends MongoRepository<Lifeteen, String> {
    Optional<Integer> countAllBy(String id);

}
