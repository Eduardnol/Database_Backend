package com.example.database_user.controllers.api;

import com.example.database_user.model.dto.Persona.PersonaDTO;
import com.example.database_user.model.dto.Persona.PersonaNinos;
import com.example.database_user.model.dto.Persona.SimplePersona;
import com.example.database_user.model.dto.discipulado.DiscipuladoMenoresDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "DiscipuladoMenores", description = "Obtain all information relative to the DiscipuladoMenores")
public interface DiscipuladoMenoresAPI {

  @Operation(summary = "Get a DiscipuladoMenoresDTO record by its id")
  @GetMapping("/{id}")
  ResponseEntity<DiscipuladoMenoresDTO> fetchDiscipuladoMenoresById(@PathVariable String id);

  @Operation(summary = "Get all DiscipuladoMenoresDTO records")
  @GetMapping("/all")
  ResponseEntity<List<DiscipuladoMenoresDTO>> fetchAllDiscipuladoMenores();

  @Operation(summary = "Get all inscritos of a DiscipuladoMenoresDTO record")
  @GetMapping("/{id}/inscritos")
  ResponseEntity<List<SimplePersona>> fetchDiscipuladoMenoresInscritosById(
      @PathVariable String id);

  @Operation(summary = "Get all monitors of a DiscipuladoMenoresDTO record")
  @GetMapping("/{id}/monitores")
  ResponseEntity<List<PersonaDTO>> fetchDiscipuladoMenoresMonitoresById(
      @PathVariable String id);

  @Operation(summary = "Insert a new DiscipuladoMenoresDTO record")
  //TODO verify the valid command
  @PostMapping("/insert")
  ResponseEntity<String> insertNewDiscipuladoMenores(
      @RequestBody @Valid DiscipuladoMenoresDTO discipuladoMenoresDTO);

  @Operation(summary = "Delete a DiscipuladoMenoresDTO record by its id")
  @DeleteMapping("/delete/{id}")
  ResponseEntity<String> deleteDiscipuladoMenores(@PathVariable String id);

  @Operation(summary = "Update a DiscipuladoMenoresDTO record")
  @PutMapping("/update")
  ResponseEntity<String> updateDiscipuladoMenores(
      @RequestBody @Valid DiscipuladoMenoresDTO discipuladoMenoresDTO);


  @Operation(summary = "Insert a new inscription")
  @PostMapping("/insert-inscription/{id-discipulado-menores}")
  ResponseEntity<String> insertNewInscription(
      @Parameter(description = "Persona Niños object") @RequestBody @Valid PersonaNinos personaNinos,
      @Parameter(description = "Id Discipulado menores where to perform the inscription") @PathVariable("id-discipulado-menores") String iddiscipuladomenores);


}
