package com.example.database_user.services;

import com.example.database_user.dtos.Lifeteen;
import com.example.database_user.dtos.Persona.InscritoNinos;
import com.example.database_user.repositories.LifeteenRepository;
import com.example.database_user.repositories.PersonaRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class LifeteenService {
	private final LifeteenRepository lifeteenRepository;
	private final PersonaRepository personaRepository;
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


	public ResponseEntity<String> addNewInscription(InscritoNinos inscritoNinos, String idLifeteen) {


		final HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		String message = "";


		//Insert to standard repository
		personaRepository.insert(inscritoNinos);
		//Insert id to Lifeteen
		Optional<Lifeteen> lifeteen = lifeteenRepository.findById(idLifeteen);
		lifeteen.ifPresent(value -> value.getIdInscritos().add(inscritoNinos.getId()));

		message = "Person correctly inserted";

		return new ResponseEntity<>(message, httpHeaders, HttpStatus.CREATED);
	}


	//TODO: hay que encontrar la manera de que una persona pueda estar en varios repositorios, actualizando sin eliminar los anteriores
	public ResponseEntity<String> addExistingInscription(String idPersonaExistente, InscritoNinos inscritoNinos, String idLifeteen) {

		//Eliminamos la persona y la introducimos de nuevo
		personaRepository.deleteById(idPersonaExistente);
		return this.addNewInscription(inscritoNinos, idLifeteen);

	}
}
