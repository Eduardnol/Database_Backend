package com.example.database_user.repositories;

import com.example.database_user.dtos.DiscipuladoMenores;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DiscipuladoMenoresRepository extends MongoRepository<DiscipuladoMenores, String> {

    Optional<Integer> countAllBy(String id);

}
