package com.example.database_user.Model.Emaus;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/emmaus")
@AllArgsConstructor
public class EmmausController {
    private final EmmausService emmausService;


    @GetMapping("/allemmaus")
    public ResponseEntity<List<Emaus>> getAllEmmaus() {
        //Do not query the people inside
    }



}