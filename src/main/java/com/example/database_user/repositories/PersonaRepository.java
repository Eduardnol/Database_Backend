package com.example.database_user.repositories;

import com.example.database_user.repositories.entity.PersonaEntity;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaRepository extends MongoRepository<PersonaEntity, String> {

    Optional<List<PersonaEntity>> findAllByBirthdayBetween(LocalDate start, LocalDate end);

    @Query("$expr: {$and: [{ $eq: [{ $dayOfMonth: '$dob' }, { $dayOfMonth: new Date() }] }," +
            "{ $eq: [{ $month: '$dob' }, { $month: new Date() }] },],},}")
    List<PersonaEntity> findCustomByRegExDomain();

    List<PersonaEntity> findAllByNombre(String nombre);

}

