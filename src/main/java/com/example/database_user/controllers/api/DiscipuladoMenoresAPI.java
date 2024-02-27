package com.example.database_user.controllers.api;

import com.example.database_user.model.dto.DiscipuladoMenoresDTO;
import com.example.database_user.model.dto.Persona.PersonaDTO;
import com.example.database_user.model.dto.Persona.PersonaNinos;
import com.example.database_user.model.dto.Persona.SimplePersona;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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

  @GetMapping("/{id}")
  ResponseEntity<DiscipuladoMenoresDTO> fetchDiscipuladoMenoresById(@PathVariable String id);

  @GetMapping("/all")
  ResponseEntity<List<DiscipuladoMenoresDTO>> fetchAllDiscipuladoMenores();

  @GetMapping("/{id}/inscritos")
  ResponseEntity<List<SimplePersona>> fetchDiscipuladoMenoresInscritosById(
      @PathVariable String id);

  @GetMapping("/{id}/monitores")
  ResponseEntity<List<PersonaDTO>> fetchDiscipuladoMenoresMonitoresById(
      @PathVariable String id);


  //TODO verify the valid command
  @PostMapping("/insert")
  ResponseEntity<String> insertNewDiscipuladoMenores(
      @RequestBody @Valid DiscipuladoMenoresDTO discipuladoMenoresDTO);

  @DeleteMapping("/delete/{id}")
  ResponseEntity<String> deleteDiscipuladoMenores(@PathVariable String id);

  @Operation(summary = "Update a DiscipuladoMenoresDTO record")
  @ApiResponse(responseCode = "200", description = "DiscipuladoMenoresDTO record updated successfully", content =
  @Content(mediaType = "text/plain"))
  @ApiResponse(responseCode = "400", description = "Invalid request payload")
  @PutMapping("/update")
  ResponseEntity<String> updateDiscipuladoMenores(
      @RequestBody @Valid DiscipuladoMenoresDTO discipuladoMenoresDTO);


  @Operation(summary = "Insert a new inscription")
  @ApiResponse(responseCode = "200", description = "Inscription inserted successfully", content =
  @Content(mediaType = "text/plain"))
  @ApiResponse(responseCode = "400", description = "Invalid request payload")
  @ApiResponse(responseCode = "404", description = "DiscipuladoMenoresDTO not found")
  @ApiResponse(responseCode = "500", description = "Internal server error")
  @PostMapping("/insert-inscription/{id-discipulado-menores}")
  ResponseEntity<String> insertNewInscription(
      @Parameter(description = "Persona Niños object") @RequestBody @Valid PersonaNinos personaNinos,
      @Parameter(description = "Id Discipulado menores where to perform the inscription") @PathVariable String iddiscipuladomenores);


}
