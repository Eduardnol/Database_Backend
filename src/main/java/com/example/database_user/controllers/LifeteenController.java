package com.example.database_user.controllers;


import com.example.database_user.dtos.Lifeteen;
import com.example.database_user.dtos.Persona.Persona;
import com.example.database_user.dtos.Persona.PersonaNinos;
import com.example.database_user.dtos.Persona.SimplePersona;
import com.example.database_user.services.LifeteenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/lifeteen")
@AllArgsConstructor
public class LifeteenController {
    private final LifeteenService lifeteenService;


    @GetMapping("/all")
    public ResponseEntity<List<Lifeteen>> fetchAllLifeteen() {

        return lifeteenService.fetchAllLifeteen();
    }

    @GetMapping("/{id}/inscritos")
    public ResponseEntity<List<SimplePersona>> fetchLifeteenInscritosById(@PathVariable String id) {

        return lifeteenService.getLifeteenInscritosById(id);
    }

    @GetMapping("/{id}/monitores")
    public ResponseEntity<List<Persona>> fetchLifeteenMonitoresById(@PathVariable String id) {

        return lifeteenService.getLifeteenMonisById(id);
    }

    @PostMapping("/insert")
    public ResponseEntity<String> insertNewLifeteen(@RequestBody @Valid Lifeteen lifeteen) {

        return lifeteenService.insertNewLifeteen(lifeteen);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteLifeteen(@PathVariable String id) {

        return lifeteenService.deleteLifeteenById(id);
    }


    @PutMapping("/update")
    public ResponseEntity<String> updateLifeteen(@RequestBody @Valid Lifeteen lifeteen) {

        return lifeteenService.updateLifeteen(lifeteen);
    }

    @Operation(operationId = "inserNewInscription", summary = "Insert a new inscription to one lifeteen", tags = {"Lifeteen"},
            parameters = {@Parameter(in = ParameterIn.PATH, name = "inscritoNinos", description = "The one to be inserted"),
                    @Parameter(name = "idlifeteen", description = "Id of the Lifeteen where we want to insert it")},
            responses = {@ApiResponse(responseCode = "200", description = "User correctly inscribed", content = @Content(schema = @Schema(implementation = PersonaNinos.class))),
                    @ApiResponse(responseCode = "404", description = "Employee not found")}
    )
    @PostMapping("/insert-inscription/{idlifeteen}")
    public ResponseEntity<String> inserNewInscription(@RequestBody @Valid PersonaNinos personaNinos, @PathVariable String idlifeteen) {

        //return lifeteenService.addNewUserNewInsciption(personaNinos, idlifeteen);
        return null;
    }


}
