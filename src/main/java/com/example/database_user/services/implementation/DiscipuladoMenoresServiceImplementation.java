package com.example.database_user.services.implementation;

import com.example.database_user.model.dto.PersonGroups;
import com.example.database_user.model.dto.Persona.PersonaDTO;
import com.example.database_user.model.dto.Persona.SimplePersona;
import com.example.database_user.model.dto.discipulado.DiscipuladoMenoresDTO;
import com.example.database_user.model.mapper.DiscipuladoMenoresMapper;
import com.example.database_user.repositories.DiscipuladoMenoresRepository;
import com.example.database_user.services.DiscipuladoMenoresService;
import com.example.database_user.services.PersonaService;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DiscipuladoMenoresServiceImplementation implements DiscipuladoMenoresService {

  @Autowired
  private DiscipuladoMenoresRepository discipuladoMenoresRepository;
  @Autowired
  private PersonaService personaServiceImplementation;
  @Autowired
  private DiscipuladoMenoresMapper discipuladoMenoresMapper;

  /***************************DiscipuladoMenoresDTO as a service***************************/
  @Override
  public ResponseEntity<DiscipuladoMenoresDTO> getDiscipuladoMenoresById(String id) {
    Optional<DiscipuladoMenoresDTO> discipuladoMenores = discipuladoMenoresRepository.findById(id)
        .map(discipuladoMenoresMapper::toDTO);

    final HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
    HttpStatus status = HttpStatus.OK;
    String message = "";

    if (discipuladoMenores.isPresent()) {
      return new ResponseEntity<>(discipuladoMenores.get(), httpHeaders, status);
    } else {
      status = HttpStatus.NOT_FOUND;
      message = "The specified ID does not exist in this repository";
      return new ResponseEntity<>(null, httpHeaders, status);
    }
  }

  /**
   * Returns all the DiscipuladoMenoresDTO instances from the database
   *
   * @return The list of DiscipuladoMenoresDTO instances
   */
  @Override
  public ResponseEntity<List<DiscipuladoMenoresDTO>> fetchAllDiscipuladoMenores() {

    HttpStatus status = HttpStatus.ACCEPTED;
    List<DiscipuladoMenoresDTO> posts = discipuladoMenoresRepository.findAll()
        .stream()
        .map(discipuladoMenoresMapper::toDTO)
        .collect(Collectors.toList());

    status = HttpStatus.OK;
    return new ResponseEntity<>(posts, status);

  }

  /**
   * Returns the DiscipuladoMenoresDTO Inscritos instance with the given id
   *
   * @param id The id of the DiscipuladoMenoresDTO instance
   * @return The DiscipuladoMenoresDTO instance
   */
  @Override
  public ResponseEntity<List<SimplePersona>> getDiscipuladoMenoresInscritosById(String id) {
    HttpStatus status = HttpStatus.ACCEPTED;
    Optional<DiscipuladoMenoresDTO> post = discipuladoMenoresRepository.findById(id)
        .map(discipuladoMenoresMapper::toDTO);
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
   * Returns the DiscipuladoMenoresDTO Monitores instance with the given id
   *
   * @param id The id of the DiscipuladoMenoresDTO instance
   * @return The DiscipuladoMenoresDTO instance
   */
  @Override
  public ResponseEntity<List<PersonaDTO>> getDiscipuladoMenoresMonisById(String id) {
    HttpStatus status = HttpStatus.ACCEPTED;
    Optional<DiscipuladoMenoresDTO> discipuladoMenoresResult = discipuladoMenoresRepository
        .findById(id)
        .map(discipuladoMenoresMapper::toDTO);
    if (discipuladoMenoresResult.isPresent()) {
      status = HttpStatus.OK;
      DiscipuladoMenoresDTO discipuladoMenoresDTO = discipuladoMenoresResult.get();
      List<PersonaDTO> lista = new ArrayList<>();
      return new ResponseEntity<>(lista, status);
    } else {
      status = HttpStatus.NOT_FOUND;
      return new ResponseEntity<>(null, status);
    }
  }

  /**
   * Inserts a new DiscipuladoMenoresDTO instance into the database
   *
   * @param discipuladoMenoresDTO the DiscipuladoMenoresDTO instance to be inserted
   * @return The status of the response
   */
  @Override
  public ResponseEntity<String> insertNewDiscipuladoMenores(
      DiscipuladoMenoresDTO discipuladoMenoresDTO) {
    discipuladoMenoresDTO.setStartDate(LocalDate.now());
    discipuladoMenoresRepository.insert(discipuladoMenoresMapper.toEntity(discipuladoMenoresDTO));
    return new ResponseEntity<>(HttpStatus.CREATED);

  }


  /**
   * Deletes a DiscipuladoMenoresDTO instance by id from the database
   *
   * @param id the DiscipuladoMenoresDTO's id
   * @return The status of the response
   */
  @Override
  public ResponseEntity<String> deleteDiscipuladoMenoresById(String id) {

    discipuladoMenoresRepository.deleteById(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }


  /**
   * Deletes the discipuladoMenoresDTO instance and creates it again with the new information
   *
   * @param discipuladoMenoresDTO the new information
   * @return The status of the response
   */
  @Override
  public ResponseEntity<String> updateDiscipuladoMenores(
      DiscipuladoMenoresDTO discipuladoMenoresDTO) {

    if (discipuladoMenoresDTO.getId() == null) {
      this.insertNewDiscipuladoMenores(discipuladoMenoresDTO);
      return new ResponseEntity<>(HttpStatus.CREATED);
    }
    discipuladoMenoresRepository.deleteById(discipuladoMenoresDTO.getId());
    discipuladoMenoresRepository.insert(discipuladoMenoresMapper.toEntity(discipuladoMenoresDTO));
    return new ResponseEntity<>(HttpStatus.OK);
  }

  /***************************DiscipuladoMenoresDTO inscribed***************************/

  /**
   * Adds a new inscription to the database when a user does not exist in our Person collection
   *
   * @param personaDTO           the new inscription
   * @param idDiscipuladoMenores the id of the discipuladoMenores where we want the inscription
   * @return The status of the response
   */
  @Override
  public ResponseEntity<String> addNewUserNewInsciption(
      PersonaDTO personaDTO,
      String idDiscipuladoMenores) {

    final HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
    String message = "";
    HttpStatus status = HttpStatus.CREATED;
    //Todo does the insertion of a InscritoNinos affects when we fetch normal persons?
    //Insert to standard Person repository
    personaServiceImplementation.insertNewPerson(personaDTO);
    //Insert id to DiscipuladoMenoresDTO
    Optional<DiscipuladoMenoresDTO> discipuladoMenores = discipuladoMenoresRepository.findById(
            idDiscipuladoMenores)
        .map(discipuladoMenoresMapper::toDTO);

    if (discipuladoMenores.isPresent()) {
      discipuladoMenores.get()
          .getIdInscritos()
          .add(new SimplePersona(personaDTO.getId(), personaDTO.getNombre(),
              personaDTO.getApellido()));
      //We pass argument as null because it's already in the database
      return this.addExistingUserExistingInscription(idDiscipuladoMenores, personaDTO.getId());
      //message = "Person correctly inserted";
    } else {
      status = HttpStatus.NOT_FOUND;
      message = "The specified discipuladoMenores ID does not exist in this repository";
    }

    return new ResponseEntity<>(message, httpHeaders, status);
  }


  /**
   * If the user already exists in the database and has InscritoNinos information, we will add them
   * into the DiscipuladoMenoresDTO repo by id
   *
   * @param idDiscipuladoMenores the id of the discipuladoMenores where we want the inscription
   * @param idPerson             the id of the person with all the information present
   */
  @Override
  public ResponseEntity<String> addExistingUserExistingInscription(
      String idDiscipuladoMenores,
      String idPerson) {

    //Insert the person into the DiscipuladoMenoresDTO repository
    final HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
    Optional<DiscipuladoMenoresDTO> discipuladoMenores = discipuladoMenoresRepository
        .findById(idDiscipuladoMenores)
        .map(discipuladoMenoresMapper::toDTO);
    String message = "";
    HttpStatus status = HttpStatus.CREATED;
    if (discipuladoMenores.isPresent()) {
      PersonaDTO personaDTO = personaServiceImplementation.findPersonById(idPerson).getBody();
      discipuladoMenores.get()
          .getIdInscritos()
          .add(new SimplePersona(personaDTO.getId(), personaDTO.getNombre(),
              personaDTO.getApellido()));
      discipuladoMenoresRepository.deleteById(idDiscipuladoMenores);
      discipuladoMenoresRepository.insert(
          discipuladoMenoresMapper.toEntity(discipuladoMenores.get()));
      //If the user is not in any group we will add them into one
      if (personaDTO.getPersonGroups() != null) {
        personaDTO.getPersonGroups()
            .add(new PersonGroups(idDiscipuladoMenores,
                discipuladoMenores.get().getTitle()));
        personaServiceImplementation.updatePerson(personaDTO);
      } else {
        personaDTO.setPersonGroups(new ArrayList<>());
        personaDTO.getPersonGroups()
            .add(new PersonGroups(idDiscipuladoMenores,
                discipuladoMenores.get().getTitle()));
        personaServiceImplementation.updatePerson(personaDTO);
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
   * @param personaDTO           new information of the user
   * @param idDiscipuladoMenores id of the discipuladoMenores that the user is going to be added to
   * @return message with the result of the operation
   */
  @Override
  public ResponseEntity<String> addExistingUserNewInscription(
      PersonaDTO personaDTO,
      String idDiscipuladoMenores) {

    //Update the Person repository with the new information
    personaServiceImplementation.updatePerson(personaDTO);
    //Insert the person into the DiscipuladoMenoresDTO repository
    return this.addExistingUserExistingInscription(idDiscipuladoMenores, personaDTO.getId());

  }


  /**
   * Edit an inscription in the database
   *
   * @param personaDTO the new information of the inscription
   * @return the status of the response
   */
  @Override
  public ResponseEntity<String> editExistingUserInscription(PersonaDTO personaDTO) {

    final HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
    String message = "";
    HttpStatus status = HttpStatus.CREATED;

    personaServiceImplementation.updatePerson(personaDTO);

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
  @Override
  public ResponseEntity<String> deleteExistingInscriptionFromADiscipuladoMenores(
      String idDiscipuladoMenores, String idPersona) {

    final HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
    String message = "";
    HttpStatus status = HttpStatus.CREATED;
    Optional<DiscipuladoMenoresDTO> discipuladoMenores = discipuladoMenoresRepository.findById(
        idDiscipuladoMenores).map(discipuladoMenoresMapper::toDTO);

    if (discipuladoMenores.isPresent()) {
      //remove from the list if object id equals iddiscipuladoMenores
      discipuladoMenores.get().getIdInscritos()
          .removeIf(persona -> persona.getId().equals(idPersona));
      discipuladoMenoresRepository.save(
          discipuladoMenoresMapper.toEntity(discipuladoMenores.get()));
      personaServiceImplementation.findPersonById(idPersona)
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

  /***************************DiscipuladoMenoresDTO Stats***************************/
  @Override
  public Integer countInscritos(String idDiscipuladoMenores) {
    Optional<DiscipuladoMenoresDTO> discipuladosMenores = discipuladoMenoresRepository.findById(
        idDiscipuladoMenores).map(discipuladoMenoresMapper::toDTO);
    return discipuladosMenores.map(
            discipuladoMenoresDTO -> discipuladoMenoresDTO.getIdInscritos().size())
        .orElse(0);
  }


}

