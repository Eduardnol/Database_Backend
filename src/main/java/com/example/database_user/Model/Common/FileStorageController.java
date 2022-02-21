package com.example.database_user.Model.Common;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/files")
@AllArgsConstructor
public class FileStorageController {

    private final FileStorageService fileStorageService;


    @GetMapping("/allpeople")
    public ResponseEntity<List<FileStorage>> fetchAllPeople() {

        return ResponseEntity.status(HttpStatus.OK).body(fileStorageService.fetchAllFiles());
    }

}
