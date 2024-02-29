package com.example.database_user.services;

import com.example.database_user.model.dto.Persona.PersonaDTO;
import com.example.database_user.model.dto.Persona.SimplePersona;
import com.example.database_user.model.dto.discipulado.DiscipuladoMenoresDTO;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface DiscipuladoMenoresService {

  ResponseEntity<DiscipuladoMenoresDTO> getDiscipuladoMenoresById(String id);

  ResponseEntity<List<DiscipuladoMenoresDTO>> fetchAllDiscipuladoMenores();

  ResponseEntity<List<SimplePersona>> getDiscipuladoMenoresInscritosById(String id);

  ResponseEntity<List<PersonaDTO>> getDiscipuladoMenoresMonisById(String id);

  ResponseEntity<String> insertNewDiscipuladoMenores(
      DiscipuladoMenoresDTO discipuladoMenoresDTO);

  ResponseEntity<String> deleteDiscipuladoMenoresById(String id);

  ResponseEntity<String> updateDiscipuladoMenores(DiscipuladoMenoresDTO discipuladoMenoresDTO);

  ResponseEntity<String> addNewUserNewInsciption(
      PersonaDTO personaDTO,
      String idDiscipuladoMenores);

  ResponseEntity<String> addExistingUserExistingInscription(
      String idDiscipuladoMenores,
      String idPerson);

  ResponseEntity<String> addExistingUserNewInscription(
      PersonaDTO personaDTO,
      String idDiscipuladoMenores);

  ResponseEntity<String> editExistingUserInscription(PersonaDTO personaDTO);

  ResponseEntity<String> deleteExistingInscriptionFromADiscipuladoMenores(
      String idDiscipuladoMenores, String idPersona);

  Integer countInscritos(String idDiscipuladoMenores);
}
    
    
    
    
