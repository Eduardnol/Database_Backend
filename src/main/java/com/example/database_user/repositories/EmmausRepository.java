package com.example.database_user.repositories;

import com.example.database_user.repositories.entity.EmmausEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmmausRepository extends MongoRepository<EmmausEntity, String> {

}
