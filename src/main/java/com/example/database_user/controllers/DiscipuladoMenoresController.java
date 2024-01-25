package com.example.database_user.controllers;


import com.example.database_user.controllers.api.DiscipuladoMenoresAPI;
import com.example.database_user.controllers.dto.DiscipuladoMenoresDTO;
import com.example.database_user.controllers.dto.Persona.PersonaDTO;
import com.example.database_user.controllers.dto.Persona.PersonaNinos;
import com.example.database_user.controllers.dto.Persona.SimplePersona;
import com.example.database_user.domain.service.DiscipuladoMenoresService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/discipuladomenores")
public class DiscipuladoMenoresController implements DiscipuladoMenoresAPI {

  @Autowired
  private DiscipuladoMenoresService discipuladoMenoresServiceImplementation;

  @Override
  @GetMapping("/{id}")
  public ResponseEntity<DiscipuladoMenoresDTO> fetchDiscipuladoMenoresById(
      @PathVariable String id) {

    return discipuladoMenoresServiceImplementation.getDiscipuladoMenoresById(id);
  }

  @Override
  @GetMapping("/all")
  public ResponseEntity<List<DiscipuladoMenoresDTO>> fetchAllDiscipuladoMenores() {

    return discipuladoMenoresServiceImplementation.fetchAllDiscipuladoMenores();
  }

  @Override
  @GetMapping("/{id}/inscritos")
  public ResponseEntity<List<SimplePersona>> fetchDiscipuladoMenoresInscritosById(
      @PathVariable String id) {

    return discipuladoMenoresServiceImplementation.getDiscipuladoMenoresInscritosById(id);
  }

  @Override
  @GetMapping("/{id}/monitores")
  public ResponseEntity<List<PersonaDTO>> fetchDiscipuladoMenoresMonitoresById(
      @PathVariable String id) {

    return discipuladoMenoresServiceImplementation.getDiscipuladoMenoresMonisById(id);
  }

  @Override
  @PostMapping("/insert")
  public ResponseEntity<String> insertNewDiscipuladoMenores(
      @RequestBody @Valid DiscipuladoMenoresDTO discipuladoMenoresDTO) {

    return discipuladoMenoresServiceImplementation.insertNewDiscipuladoMenores(
        discipuladoMenoresDTO);
  }

  @Override
  @DeleteMapping("/delete/{id}")
  public ResponseEntity<String> deleteDiscipuladoMenores(@PathVariable String id) {

    return discipuladoMenoresServiceImplementation.deleteDiscipuladoMenoresById(id);
  }

  @Override
  @PutMapping("/update")
  public ResponseEntity<String> updateDiscipuladoMenores(
      @RequestBody @Valid DiscipuladoMenoresDTO discipuladoMenoresDTO) {
    return discipuladoMenoresServiceImplementation.updateDiscipuladoMenores(discipuladoMenoresDTO);
  }

  @Override
  @PostMapping("/insert-inscription/{iddiscipuladomenores}")
  public ResponseEntity<String> inserNewInscription(
      @RequestBody @Valid PersonaNinos personaNinos,
      @PathVariable String iddiscipuladomenores) {

    //return discipuladomenoresService.addNewUserNewInsciption(personaNinos, iddiscipuladomenores);
    return null;
  }


}
