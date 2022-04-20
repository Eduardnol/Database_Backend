package com.example.database_user.services;

import com.example.database_user.dtos.Lifeteen;
import com.example.database_user.dtos.Persona.Ninos.InscritoNinos;
import com.example.database_user.repositories.LifeteenRepository;
import com.example.database_user.repositories.PersonaRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
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


	/**
	 * Returns all the Lifeteen instances from the database
	 *
	 * @return The list of Lifeteen instances
	 */
	public ResponseEntity<List<Lifeteen>> fetchAllLifeteen() {

		HttpStatus status = HttpStatus.ACCEPTED;
		List<Lifeteen> posts = lifeteenRepository.findAll();
		return new ResponseEntity<>(posts, status);

	}


	/**
	 * Inserts a new Lifeteen instance into the database
	 *
	 * @param lifeteen the Lifeteen instance to be inserted
	 * @return The status of the response
	 */
	public ResponseEntity<String> insertNewLifeteen(Lifeteen lifeteen) {

		lifeteenRepository.insert(lifeteen);
		return new ResponseEntity<>(HttpStatus.CREATED);

	}


	/**
	 * Deletes a Lifeteen instance by id from the database
	 *
	 * @param id the Lifeteen's id
	 * @return The status of the response
	 */
	public ResponseEntity<String> deleteLifeteenById(String id) {

		lifeteenRepository.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}


	/**
	 * Deletes the lifeteen instance and creates it again with the new information
	 *
	 * @param lifeteen the new information
	 * @return The status of the response
	 */
	public ResponseEntity<String> updateLifeteen(Lifeteen lifeteen) {

		lifeteenRepository.deleteById(lifeteen.getId());
		lifeteenRepository.insert(lifeteen);
		return new ResponseEntity<>(HttpStatus.OK);
	}


	/**
	 * Adds a new inscription to the database when a user does not exist in our Person collection
	 *
	 * @param inscritoNinos the new inscription
	 * @param idLifeteen    the id of the lifeteen where we want the inscription
	 * @return The status of the response
	 */
	public ResponseEntity<String> addNewInscription(InscritoNinos inscritoNinos, String idLifeteen) {


		final HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		String message = "";
		HttpStatus status = HttpStatus.CREATED;
		//Todo does the insertion of a InscritoNinos affects when we fetch normal persons?
		//Insert to standard Person repository
		personaRepository.insert(inscritoNinos);
		//Insert id to Lifeteen
		Optional<Lifeteen> lifeteen = lifeteenRepository.findById(idLifeteen);

		if (lifeteen.isPresent()) {
			lifeteen.get().getIdInscritos().add(inscritoNinos.getId());
			//We pass argument as null because it's already in the database
			return this.addExistingUserExistingInscription(idLifeteen, inscritoNinos.getId());
			//message = "Person correctly inserted";
		} else {
			status = HttpStatus.NOT_FOUND;
			message = "The specified ID does not exist in this repository";
		}


		return new ResponseEntity<>(message, httpHeaders, status);
	}


	/**
	 * If the user already exists in the database and has InscritoNinos information, we will add them into the Lifeteen repo by id
	 *
	 * @param idLifeteen the id of the lifeteen where we want the inscription
	 * @param idPerson   the id of the person with all the information present
	 */
	public ResponseEntity<String> addExistingUserExistingInscription(String idLifeteen, String idPerson) {

		//Insert the person into the Lifeteen repository
		final HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		Optional<Lifeteen> lifeteen = lifeteenRepository.findById(idLifeteen);
		String message = "";
		HttpStatus status = HttpStatus.CREATED;
		if (lifeteen.isPresent()) {
			lifeteen.get().getIdInscritos().add(idPerson);
			lifeteenRepository.deleteById(idLifeteen);
			lifeteenRepository.insert(lifeteen.get());
			message = "User added in the specified lifeteen id";
		} else {
			status = HttpStatus.NOT_FOUND;
			message = "The specified ID does not exist in this repository";
		}
		return new ResponseEntity<>(message, httpHeaders, status);
	}

	//TODO: hay que encontrar la manera de que una persona pueda estar en varios repositorios, actualizando sin eliminar los anteriores


	/**
	 * If a user already exists in the database and does not have InscritoNinos information, it will be updated with the new information.
	 *
	 * @param idPersonaExistente id of the user that already exists in the database
	 * @param inscritoNinos      new information of the user
	 * @param idLifeteen         id of the lifeteen that the user is going to be added to
	 * @return message with the result of the operation
	 */
	public ResponseEntity<String> addExistingUserNewInscription(String idPersonaExistente, InscritoNinos inscritoNinos, String idLifeteen) {


		//Update the Person repository with the new information
		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(idPersonaExistente));
		Update update = new Update();
		//We write the required information by only adding the subclass fields
		update.set("infoInscripcionMenor", inscritoNinos.getInfoInscripcionMenor());
		mongoTemplate.updateFirst(query, update, "persona");

		//Insert the person into the Lifeteen repository
		return this.addExistingUserExistingInscription(idLifeteen, idPersonaExistente);

	}


	/**
	 * Edit an inscription in the database
	 *
	 * @param inscritoNinos the new information of the inscription
	 * @return the status of the response
	 */
	public ResponseEntity<String> editExistingUserInscription(InscritoNinos inscritoNinos) {

		final HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		String message = "";
		HttpStatus status = HttpStatus.CREATED;

		InscritoNinos.InnerIncritoNinos innerInscritoNinos = inscritoNinos.getInfoInscripcionMenor();


		//Update the Person repository with the new information
		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(inscritoNinos.getId()));
		Update update = new Update();
		//We write the required information by only adding the subclass fields
		update.set("infoInscripcionMenor", inscritoNinos.getInfoInscripcionMenor());
		mongoTemplate.updateFirst(query, update, "lifeteen");
		message = "Inscription updated";
		status = HttpStatus.OK;

		return new ResponseEntity<>(message, httpHeaders, status);
	}


	/**
	 * Delete an inscription from an specific lifeteen (kept on database)
	 *
	 * @param idLifeteen the id of the lifeteen where the inscription is
	 * @param idPersona  the id of the person that is going to be deleted
	 * @return the status of the response
	 */
	public ResponseEntity<String> deleteExistingInscriptionFromALifeteen(String idLifeteen, String idPersona) {

		final HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		String message = "";
		HttpStatus status = HttpStatus.CREATED;
		Optional<Lifeteen> lifeteen = lifeteenRepository.findById(idLifeteen);


		if (lifeteen.isPresent()) {
			lifeteen.get().getIdInscritos().remove(idPersona);
			lifeteenRepository.insert(lifeteen.get());
			status = HttpStatus.OK;
			message = "The user has been deleted from the specified lifeteen";
		} else {
			status = HttpStatus.NOT_FOUND;
			message = "The specified ID does not exist in this repository";
		}

		return new ResponseEntity<>(message, httpHeaders, status);

	}
}

