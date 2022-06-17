package com.example.database_user.services;

import com.example.database_user.dtos.Lifeteen;
import com.example.database_user.dtos.PersonGroups;
import com.example.database_user.dtos.Persona.Persona;
import com.example.database_user.repositories.LifeteenRepository;
import com.example.database_user.repositories.PersonaRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class LifeteenService {
    private final LifeteenRepository lifeteenRepository;
    private final PersonaRepository personaRepository;
    private final MongoTemplate mongoTemplate;
    private final PersonaService personaService;

    /***************************Lifeteen as a service***************************/

    /**
     * Returns all the Lifeteen instances from the database
     *
     * @return The list of Lifeteen instances
     */
    public ResponseEntity<List<Lifeteen>> fetchAllLifeteen() {

        HttpStatus status = HttpStatus.ACCEPTED;
        Persona persona1;
        Persona persona2;
        List<Lifeteen> posts = lifeteenRepository.findAll();

        //Change the ID by the actual name
        for (Lifeteen post : posts) {
            persona1 = personaRepository.findById(posts.get(0).getResponsable1()).get();
            persona2 = personaRepository.findById(posts.get(0).getResponsable2()).get();
            post.setResponsable1(persona1.getNombre() + ' ' + persona1.getApellido());
            post.setResponsable2(persona2.getNombre() + ' ' + persona2.getApellido());
        }

        status = HttpStatus.OK;
        return new ResponseEntity<>(posts, status);

    }

    /**
     * Returns the Lifeteen Inscritos instance with the given id
     *
     * @param id The id of the Lifeteen instance
     * @return The Lifeteen instance
     */
    public ResponseEntity<List<Persona>> getLifeteenInscritosById(String id) {
        HttpStatus status = HttpStatus.ACCEPTED;
        Optional<Lifeteen> post = lifeteenRepository.findById(id);
        if (post.isPresent()) {
            status = HttpStatus.OK;
            Lifeteen lifeteen = post.get();
            List<Persona> lista = new ArrayList<>();
            for (String idIndiv : lifeteen.getIdInscritos()) {
                Persona persona = personaService.findPersonById(idIndiv).getBody();
                lista.add(persona);
            }
            return new ResponseEntity<>(lista, status);
        } else {
            status = HttpStatus.NOT_FOUND;
            return new ResponseEntity<>(null, status);
        }
    }

    /**
     * Returns the Lifeteen Monitores instance with the given id
     *
     * @param id The id of the Lifeteen instance
     * @return The Lifeteen instance
     */
    public ResponseEntity<List<Persona>> getLifeteenMonisById(String id) {
        HttpStatus status = HttpStatus.ACCEPTED;
        Optional<Lifeteen> lifeteenResult = lifeteenRepository.findById(id);
        if (lifeteenResult.isPresent()) {
            status = HttpStatus.OK;
            Lifeteen lifeteen = lifeteenResult.get();
            List<Persona> lista = new ArrayList<>();
            for (String idIndiv : lifeteen.getIdMonitores()) {
                Persona persona = personaService.findPersonById(idIndiv).getBody();
                lista.add(persona);
            }
            return new ResponseEntity<>(lista, status);
        } else {
            status = HttpStatus.NOT_FOUND;
            return new ResponseEntity<>(null, status);
        }
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

    /***************************Lifeteen inscribed***************************/

    /**
     * Adds a new inscription to the database when a user does not exist in our Person collection
     *
     * @param persona    the new inscription
     * @param idLifeteen the id of the lifeteen where we want the inscription
     * @return The status of the response
     */
    public ResponseEntity<String> addNewUserNewInsciption(Persona persona, String idLifeteen) {


        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        String message = "";
        HttpStatus status = HttpStatus.CREATED;
        //Todo does the insertion of a InscritoNinos affects when we fetch normal persons?
        //Insert to standard Person repository
        personaService.insertNewPerson(persona);
        //Insert id to Lifeteen
        Optional<Lifeteen> lifeteen = lifeteenRepository.findById(idLifeteen);

        if (lifeteen.isPresent()) {
            lifeteen.get().getIdInscritos().add(persona.getId());
            //We pass argument as null because it's already in the database
            return this.addExistingUserExistingInscription(idLifeteen, persona.getId());
            //message = "Person correctly inserted";
        } else {
            status = HttpStatus.NOT_FOUND;
            message = "The specified lifeteen ID does not exist in this repository";
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
            Persona persona = personaService.findPersonById(idPerson).getBody();
            if (persona.getPersonGroups() != null) {
                persona.getPersonGroups().add(new PersonGroups(idLifeteen, lifeteen.get().getTitle()));
                personaService.updatePerson(persona);
            } else {
                persona.setPersonGroups(new ArrayList<>());
                persona.getPersonGroups().add(new PersonGroups(idLifeteen, lifeteen.get().getTitle()));
                personaService.updatePerson(persona);
            }
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
     * @param persona    new information of the user
     * @param idLifeteen id of the lifeteen that the user is going to be added to
     * @return message with the result of the operation
     */
    public ResponseEntity<String> addExistingUserNewInscription(Persona persona, String idLifeteen) {


        //Update the Person repository with the new information
        personaService.updatePerson(persona);
        //Insert the person into the Lifeteen repository
        return this.addExistingUserExistingInscription(idLifeteen, persona.getId());

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
            lifeteenRepository.save(lifeteen.get());
            personaService.findPersonById(idPersona).getBody().getPersonGroups().removeIf(personGroups -> personGroups.getId().equals(idLifeteen));
            status = HttpStatus.OK;
            message = "The user has been deleted from the specified lifeteen";
        } else {
            status = HttpStatus.NOT_FOUND;
            message = "The specified ID does not exist in this repository";
        }

        return new ResponseEntity<>(message, httpHeaders, status);

    }

    /***************************Lifeteen Stats***************************/
    public Integer countInscritos(String idLifeteen) {
        Optional<Lifeteen> lifeteens = lifeteenRepository.findById(idLifeteen);
        return lifeteens.map(lifeteen -> lifeteen.getIdInscritos().size()).orElse(0);
    }
}

