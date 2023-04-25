package com.example.database_user.controllers;


import com.example.database_user.dtos.DiscipuladoMenores;
import com.example.database_user.dtos.Persona.Persona;
import com.example.database_user.dtos.Persona.PersonaNinos;
import com.example.database_user.dtos.Persona.SimplePersona;
import com.example.database_user.services.DiscipuladoMenoresService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.util.List;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/discipuladomenores")
@AllArgsConstructor
public class DiscipuladoMenoresController {

  private final DiscipuladoMenoresService discipuladoMenoresService;


  @GetMapping("/all")
  public ResponseEntity<List<DiscipuladoMenores>> fetchAllDiscipuladoMenores() {

    return discipuladoMenoresService.fetchAllDiscipuladoMenores();
  }

  @GetMapping("/{id}/inscritos")
  public ResponseEntity<List<SimplePersona>> fetchDiscipuladoMenoresInscritosById(
      @PathVariable String id) {

    return discipuladoMenoresService.getDiscipuladoMenoresInscritosById(id);
  }

  @GetMapping("/{id}/monitores")
  public ResponseEntity<List<Persona>> fetchDiscipuladoMenoresMonitoresById(
      @PathVariable String id) {

    return discipuladoMenoresService.getDiscipuladoMenoresMonisById(id);
  }

  //TODO verify the valid command
  @PostMapping("/insert")
  public ResponseEntity<String> insertNewDiscipuladoMenores(
      @RequestBody @Valid DiscipuladoMenores discipuladoMenores) {

    return discipuladoMenoresService.insertNewDiscipuladoMenores(discipuladoMenores);
  }


  @DeleteMapping("/delete/{id}")
  public ResponseEntity<String> deleteDiscipuladoMenores(@PathVariable String id) {

    return discipuladoMenoresService.deleteDiscipuladoMenoresById(id);
  }


  @PutMapping("/update")
  public ResponseEntity<String> updateDiscipuladoMenores(
      @RequestBody @Valid DiscipuladoMenores discipuladoMenores) {

    return discipuladoMenoresService.updateDiscipuladoMenores(discipuladoMenores);
  }

  @Operation(operationId = "inserNewInscription", summary = "Insert a new inscription to one discipuladomenores",
      tags = {
          "DiscipuladoMenores"},
      parameters = {
          @Parameter(in = ParameterIn.PATH, name = "inscritoNinos", description = "The one to be inserted"),
          @Parameter(name = "iddiscipuladomenores", description =
              "Id of the DiscipuladoMenores where we " +
                  "want to insert it")},
      responses = {
          @ApiResponse(responseCode = "200", description = "User correctly inscribed", content =
          @Content(schema = @Schema(implementation = PersonaNinos.class))),
          @ApiResponse(responseCode = "404", description = "Employee not found")}
  )
  @PostMapping("/insert-inscription/{iddiscipuladomenores}")
  public ResponseEntity<String> inserNewInscription(
      @RequestBody @Valid PersonaNinos personaNinos,
      @PathVariable String iddiscipuladomenores) {

    //return discipuladomenoresService.addNewUserNewInsciption(personaNinos, iddiscipuladomenores);
    return null;
  }


}
