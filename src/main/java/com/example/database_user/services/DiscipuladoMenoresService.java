package com.example.database_user.services;

import com.example.database_user.dtos.DiscipuladoMenores;
import com.example.database_user.dtos.PersonGroups;
import com.example.database_user.dtos.Persona.Persona;
import com.example.database_user.dtos.Persona.SimplePersona;
import com.example.database_user.repositories.DiscipuladoMenoresRepository;
import com.example.database_user.repositories.PersonaRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class DiscipuladoMenoresService {

  private final DiscipuladoMenoresRepository discipuladoMenoresRepository;
  private final PersonaRepository personaRepository;
  private final MongoTemplate mongoTemplate;
  private final PersonaService personaService;

  /***************************DiscipuladoMenores as a service***************************/

  /**
   * Returns all the DiscipuladoMenores instances from the database
   *
   * @return The list of DiscipuladoMenores instances
   */
  public ResponseEntity<List<DiscipuladoMenores>> fetchAllDiscipuladoMenores() {

    HttpStatus status = HttpStatus.ACCEPTED;
    List<DiscipuladoMenores> posts = discipuladoMenoresRepository.findAll();

    status = HttpStatus.OK;
    return new ResponseEntity<>(posts, status);

  }

  /**
   * Returns the DiscipuladoMenores Inscritos instance with the given id
   *
   * @param id The id of the DiscipuladoMenores instance
   * @return The DiscipuladoMenores instance
   */
  public ResponseEntity<List<SimplePersona>> getDiscipuladoMenoresInscritosById(String id) {
    HttpStatus status = HttpStatus.ACCEPTED;
    Optional<DiscipuladoMenores> post = discipuladoMenoresRepository.findById(id);
    if (post.isPresent()) {
      status = HttpStatus.OK;
      List<SimplePersona> lista = new ArrayList<>();
      lista = post.get().getIdInscritos();
      return new ResponseEntity<>(lista, status);
    } else {
      status = HttpStatus.NOT_FOUND;
      return new ResponseEntity<>(null, status);
    }
  }

  /**
   * Returns the DiscipuladoMenores Monitores instance with the given id
   *
   * @param id The id of the DiscipuladoMenores instance
   * @return The DiscipuladoMenores instance
   */
  public ResponseEntity<List<Persona>> getDiscipuladoMenoresMonisById(String id) {
    HttpStatus status = HttpStatus.ACCEPTED;
    Optional<DiscipuladoMenores> discipuladoMenoresResult = discipuladoMenoresRepository.findById(
        id);
    if (discipuladoMenoresResult.isPresent()) {
      status = HttpStatus.OK;
      DiscipuladoMenores discipuladoMenores = discipuladoMenoresResult.get();
      List<Persona> lista = new ArrayList<>();
      return new ResponseEntity<>(lista, status);
    } else {
      status = HttpStatus.NOT_FOUND;
      return new ResponseEntity<>(null, status);
    }
  }

  /**
   * Inserts a new DiscipuladoMenores instance into the database
   *
   * @param discipuladoMenores the DiscipuladoMenores instance to be inserted
   * @return The status of the response
   */
  public ResponseEntity<String> insertNewDiscipuladoMenores(
      DiscipuladoMenores discipuladoMenores) {

    if (discipuladoMenores.getIdInscritos().size() == 0
        || discipuladoMenores.getIdInscritos().get(0).getId() == null) {
      discipuladoMenores.setIdInscritos(null);
    }
    if (discipuladoMenores.getIdMonitores().size() == 0
        || discipuladoMenores.getIdMonitores().get(0).getId() == null) {
      discipuladoMenores.setIdMonitores(null);
    }
    if (discipuladoMenores.getResponsables().size() == 0
        || discipuladoMenores.getResponsables().get(0).getId() == null) {
      discipuladoMenores.setResponsables(null);
    }
    if (discipuladoMenores.getSubgrupos().size() == 0
        || discipuladoMenores.getSubgrupos().get(0).getId() == null) {
      discipuladoMenores.setSubgrupos(null);
    }

    discipuladoMenoresRepository.insert(discipuladoMenores);
    return new ResponseEntity<>(HttpStatus.CREATED);

  }


  /**
   * Deletes a DiscipuladoMenores instance by id from the database
   *
   * @param id the DiscipuladoMenores's id
   * @return The status of the response
   */
  public ResponseEntity<String> deleteDiscipuladoMenoresById(String id) {

    discipuladoMenoresRepository.deleteById(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }


  /**
   * Deletes the discipuladoMenores instance and creates it again with the new information
   *
   * @param discipuladoMenores the new information
   * @return The status of the response
   */
  public ResponseEntity<String> updateDiscipuladoMenores(DiscipuladoMenores discipuladoMenores) {

    if (discipuladoMenores.getId() == null) {
      this.insertNewDiscipuladoMenores(discipuladoMenores);
      return new ResponseEntity<>(HttpStatus.CREATED);
    }
    discipuladoMenoresRepository.deleteById(discipuladoMenores.getId());
    discipuladoMenoresRepository.insert(discipuladoMenores);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  /***************************DiscipuladoMenores inscribed***************************/

  /**
   * Adds a new inscription to the database when a user does not exist in our Person collection
   *
   * @param persona              the new inscription
   * @param idDiscipuladoMenores the id of the discipuladoMenores where we want the inscription
   * @return The status of the response
   */
  public ResponseEntity<String> addNewUserNewInsciption(Persona persona,
      String idDiscipuladoMenores) {

    final HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
    String message = "";
    HttpStatus status = HttpStatus.CREATED;
    //Todo does the insertion of a InscritoNinos affects when we fetch normal persons?
    //Insert to standard Person repository
    personaService.insertNewPerson(persona);
    //Insert id to DiscipuladoMenores
    Optional<DiscipuladoMenores> discipuladoMenores = discipuladoMenoresRepository.findById(
        idDiscipuladoMenores);

    if (discipuladoMenores.isPresent()) {
      discipuladoMenores.get()
          .getIdInscritos()
          .add(new SimplePersona(persona.getId(), persona.getNombre(),
              persona.getApellido()));
      //We pass argument as null because it's already in the database
      return this.addExistingUserExistingInscription(idDiscipuladoMenores, persona.getId());
      //message = "Person correctly inserted";
    } else {
      status = HttpStatus.NOT_FOUND;
      message = "The specified discipuladoMenores ID does not exist in this repository";
    }

    return new ResponseEntity<>(message, httpHeaders, status);
  }


  /**
   * If the user already exists in the database and has InscritoNinos information, we will add them
   * into the DiscipuladoMenores repo by id
   *
   * @param idDiscipuladoMenores the id of the discipuladoMenores where we want the inscription
   * @param idPerson             the id of the person with all the information present
   */
  public ResponseEntity<String> addExistingUserExistingInscription(String idDiscipuladoMenores,
      String idPerson) {

    //Insert the person into the DiscipuladoMenores repository
    final HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
    Optional<DiscipuladoMenores> discipuladoMenores = discipuladoMenoresRepository.findById(
        idDiscipuladoMenores);
    String message = "";
    HttpStatus status = HttpStatus.CREATED;
    if (discipuladoMenores.isPresent()) {
      Persona persona = personaService.findPersonById(idPerson).getBody();
      discipuladoMenores.get()
          .getIdInscritos()
          .add(new SimplePersona(persona.getId(), persona.getNombre(),
              persona.getApellido()));
      discipuladoMenoresRepository.deleteById(idDiscipuladoMenores);
      discipuladoMenoresRepository.insert(discipuladoMenores.get());
      //If the user is not in any group we will add them into one
      if (persona.getPersonGroups() != null) {
        persona.getPersonGroups()
            .add(new PersonGroups(idDiscipuladoMenores,
                discipuladoMenores.get().getTitle()));
        personaService.updatePerson(persona);
      } else {
        persona.setPersonGroups(new ArrayList<>());
        persona.getPersonGroups()
            .add(new PersonGroups(idDiscipuladoMenores,
                discipuladoMenores.get().getTitle()));
        personaService.updatePerson(persona);
      }
      message = "User added in the specified discipuladoMenores id";
    } else {
      status = HttpStatus.NOT_FOUND;
      message = "The specified ID does not exist in this repository";
    }
    return new ResponseEntity<>(message, httpHeaders, status);
  }

  //TODO: hay que encontrar la manera de que una persona pueda estar en varios repositorios, actualizando sin
  // eliminar los anteriores


  /**
   * If a user already exists in the database and does not have InscritoNinos information, it will
   * be updated with the new information.
   *
   * @param persona              new information of the user
   * @param idDiscipuladoMenores id of the discipuladoMenores that the user is going to be added to
   * @return message with the result of the operation
   */
  public ResponseEntity<String> addExistingUserNewInscription(Persona persona,
      String idDiscipuladoMenores) {

    //Update the Person repository with the new information
    personaService.updatePerson(persona);
    //Insert the person into the DiscipuladoMenores repository
    return this.addExistingUserExistingInscription(idDiscipuladoMenores, persona.getId());

  }


  /**
   * Edit an inscription in the database
   *
   * @param persona the new information of the inscription
   * @return the status of the response
   */
  public ResponseEntity<String> editExistingUserInscription(Persona persona) {

    final HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
    String message = "";
    HttpStatus status = HttpStatus.CREATED;

    personaService.updatePerson(persona);

    message = "Inscription updated";
    status = HttpStatus.OK;

    return new ResponseEntity<>(message, httpHeaders, status);
  }


  /**
   * Delete an inscription from an specific discipuladoMenores (kept on database)
   *
   * @param idDiscipuladoMenores the id of the discipuladoMenores where the inscription is
   * @param idPersona            the id of the person that is going to be deleted
   * @return the status of the response
   */
  public ResponseEntity<String> deleteExistingInscriptionFromADiscipuladoMenores(
      String idDiscipuladoMenores, String idPersona) {

    final HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
    String message = "";
    HttpStatus status = HttpStatus.CREATED;
    Optional<DiscipuladoMenores> discipuladoMenores = discipuladoMenoresRepository.findById(
        idDiscipuladoMenores);

    if (discipuladoMenores.isPresent()) {
      //remove from the list if object id equals iddiscipuladoMenores
      discipuladoMenores.get().getIdInscritos()
          .removeIf(persona -> persona.getId().equals(idPersona));
      discipuladoMenoresRepository.save(discipuladoMenores.get());
      personaService.findPersonById(idPersona)
          .getBody()
          .getPersonGroups()
          .removeIf(personGroups -> personGroups.getId().equals(idDiscipuladoMenores));
      status = HttpStatus.OK;
      message = "The user has been deleted from the specified discipuladoMenores";
    } else {
      status = HttpStatus.NOT_FOUND;
      message = "The specified ID does not exist in this repository";
    }

    return new ResponseEntity<>(message, httpHeaders, status);

  }

  /***************************DiscipuladoMenores Stats***************************/
  public Integer countInscritos(String idDiscipuladoMenores) {
    Optional<DiscipuladoMenores> discipuladosMenores = discipuladoMenoresRepository.findById(
        idDiscipuladoMenores);
    return discipuladosMenores.map(
            discipuladoMenores -> discipuladoMenores.getIdInscritos().size())
        .orElse(0);
  }
}

