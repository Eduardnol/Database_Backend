package com.example.database_user.controllers.impl;


import com.example.database_user.controllers.api.DiscipuladoMenoresAPI;
import com.example.database_user.model.dto.Persona.PersonaDTO;
import com.example.database_user.model.dto.Persona.PersonaNinos;
import com.example.database_user.model.dto.Persona.SimplePersona;
import com.example.database_user.model.dto.discipulado.DiscipuladoMenoresDTO;
import com.example.database_user.services.DiscipuladoMenoresService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/discipuladomenores")
public class DiscipuladoMenoresController implements DiscipuladoMenoresAPI {

  @Autowired
  private DiscipuladoMenoresService discipuladoMenoresServiceImplementation;

  @Override
  public ResponseEntity<DiscipuladoMenoresDTO> fetchDiscipuladoMenoresById(
      @PathVariable String id) {

    return discipuladoMenoresServiceImplementation.getDiscipuladoMenoresById(id);
  }

  @Override
  public ResponseEntity<List<DiscipuladoMenoresDTO>> fetchAllDiscipuladoMenores() {

    return discipuladoMenoresServiceImplementation.fetchAllDiscipuladoMenores();
  }

  @Override
  public ResponseEntity<List<SimplePersona>> fetchDiscipuladoMenoresInscritosById(
      @PathVariable String id) {

    return discipuladoMenoresServiceImplementation.getDiscipuladoMenoresInscritosById(id);
  }

  @Override
  public ResponseEntity<List<PersonaDTO>> fetchDiscipuladoMenoresMonitoresById(
      @PathVariable String id) {

    return discipuladoMenoresServiceImplementation.getDiscipuladoMenoresMonisById(id);
  }

  @Override
  public ResponseEntity<String> insertNewDiscipuladoMenores(
      @RequestBody @Valid DiscipuladoMenoresDTO discipuladoMenoresDTO) {

    return discipuladoMenoresServiceImplementation.insertNewDiscipuladoMenores(
        discipuladoMenoresDTO);
  }

  @Override
  public ResponseEntity<String> deleteDiscipuladoMenores(@PathVariable String id) {

    return discipuladoMenoresServiceImplementation.deleteDiscipuladoMenoresById(id);
  }

  @Override
  public ResponseEntity<String> updateDiscipuladoMenores(
      @RequestBody @Valid DiscipuladoMenoresDTO discipuladoMenoresDTO) {
    return discipuladoMenoresServiceImplementation.updateDiscipuladoMenores(discipuladoMenoresDTO);
  }

  @Override
  public ResponseEntity<String> insertNewInscription(
      @RequestBody @Valid PersonaNinos personaNinos,
      @PathVariable String iddiscipuladomenores) {

    //return discipuladomenoresService.addNewUserNewInsciption(personaNinos, iddiscipuladomenores);
    return null;
  }


}
