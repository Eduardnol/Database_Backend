package com.example.database_user.repositories;

import com.example.database_user.repositories.entity.AuthUserEntity;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthUserRepository extends MongoRepository<AuthUserEntity, String> {

  Optional<AuthUserEntity> findByEmail(String email);

}
