package com.example.database_user.Model.Common;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/files")
@AllArgsConstructor
public class FileStorageController {

    private final FileStorageService fileStorageService;


    @GetMapping("/allfiles")
    public ResponseEntity<List<FileStorage>> fetchAllPeople(@RequestParam(name = "id") String identificador) {

        return ResponseEntity.status(HttpStatus.OK).body(fileStorageService.fetchAllFiles());
    }


    @PutMapping(value = "/update")
    public ResponseEntity<String> updateExisting(@RequestParam("file") MultipartFile file, @RequestParam(name = "id") String identificador) {

        return null;
        //return fileStorageService.addFile(file);

    }

}
