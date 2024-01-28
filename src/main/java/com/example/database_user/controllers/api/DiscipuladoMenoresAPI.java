package com.example.database_user.controllers.api;

import com.example.database_user.controllers.dto.DiscipuladoMenoresDTO;
import com.example.database_user.controllers.dto.Persona.PersonaDTO;
import com.example.database_user.controllers.dto.Persona.PersonaNinos;
import com.example.database_user.controllers.dto.Persona.SimplePersona;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

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


  @Operation(operationId = "inserNewInscription", summary = "Insert a new inscription to one discipuladomenores",
      tags = {
          "DiscipuladoMenoresDTO"},
      parameters = {
          @Parameter(in = ParameterIn.PATH, name = "inscritoNinos", description = "The one to be inserted"),
          @Parameter(name = "iddiscipuladomenores", description =
              "Id of the DiscipuladoMenoresDTO where we " +
                  "want to insert it")},
      responses = {
          @ApiResponse(responseCode = "200", description = "User correctly inscribed", content =
          @Content(schema = @Schema(implementation = PersonaNinos.class))),
          @ApiResponse(responseCode = "404", description = "Employee not found")}
  )
  @PostMapping("/insert-inscription/{iddiscipuladomenores}")
   ResponseEntity<String> inserNewInscription(
      @RequestBody @Valid PersonaNinos personaNinos,
      @PathVariable String iddiscipuladomenores);


}
