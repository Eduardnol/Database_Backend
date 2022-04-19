package com.example.database_user.services;

import com.example.database_user.repositories.LifeteenRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class LifeteenService {
	private final LifeteenRepository lifeteenRepository;
	private final MongoTemplate mongoTemplate;


}
