package com.example.database_user.controllers;

import com.example.database_user.dtos.Persona.Persona;
import com.example.database_user.services.StatisticsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/stats")
@AllArgsConstructor
public class StatisticsController {
    private final StatisticsService statisticsService;


    @GetMapping("/count_persons")
    public ResponseEntity<Integer> countAllPersons() {

        return ResponseEntity.status(HttpStatus.OK).body(statisticsService.countAllPeople());
    }


    @GetMapping("/today_birthday")
    public ResponseEntity<List<Persona>> getTodayBirthday() {

        return statisticsService.getTodayBirthdays();
    }

}
