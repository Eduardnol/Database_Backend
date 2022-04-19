package com.example.database_user.services;

import com.example.database_user.dtos.Lifeteen;
import com.example.database_user.dtos.Persona;
import com.example.database_user.repositories.LifeteenRepository;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class LifeteenService {
	private final LifeteenRepository lifeteenRepository;
	private final MongoTemplate mongoTemplate;


	public ResponseEntity<List<Lifeteen>> fetchAllLifeteen() {

		HttpStatus status = HttpStatus.ACCEPTED;
		List<Lifeteen> posts = lifeteenRepository.findAll();
		return new ResponseEntity<>(posts, status);

	}


	public ResponseEntity<String> insertNewLifeteen(Lifeteen lifeteen) {

		lifeteenRepository.insert(lifeteen);
		return new ResponseEntity<>(HttpStatus.CREATED);

	}


	public ResponseEntity<String> deleteById(String id) {

		lifeteenRepository.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}


	public ResponseEntity<String> updateLifeteen(Lifeteen lifeteen) {

		System.out.println("The corresponding id is:" + lifeteen.getId());
		lifeteenRepository.deleteById(lifeteen.getId());
		lifeteenRepository.insert(lifeteen);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}

