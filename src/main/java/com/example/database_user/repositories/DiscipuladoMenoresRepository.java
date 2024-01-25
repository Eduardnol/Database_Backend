package com.example.database_user.repositories;

import com.example.database_user.controllers.dto.DiscipuladoMenoresDTO;
import java.util.Optional;

import com.example.database_user.repositories.entity.DiscipuladoMenoresEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscipuladoMenoresRepository extends MongoRepository<DiscipuladoMenoresEntity, String> { }
