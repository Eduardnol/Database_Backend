package com.example.database_user.repositories;

import com.example.database_user.repositories.entity.UserEntity;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<UserEntity, String> {

  Optional<UserEntity> findByEmail(String email);

}
