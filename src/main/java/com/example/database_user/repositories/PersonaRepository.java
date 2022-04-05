package com.example.database_user.repositories;

import com.example.database_user.dtos.Persona;
import org.bson.Document;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PersonaRepository extends MongoRepository<Persona, String> {
	Optional<List<Persona>> findAllByBirthdayBetween(LocalDate start, LocalDate end);

	@Query("$expr: {$and: [{ $eq: [{ $dayOfMonth: '$dob' }, { $dayOfMonth: new Date() }] }," +
			"{ $eq: [{ $month: '$dob' }, { $month: new Date() }] },],},}")
	List<Persona> findCustomByRegExDomain();
}

