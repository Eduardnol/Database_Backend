package com.example.database_user.model.dto.Reponses;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class FileStorageResponse {

    private String name;
    private String url;


    public FileStorageResponse(String message, HttpStatus status, String name, String url) {
    }


    private FileStorageResponse(String name, String url) {

        super();
        this.name = name;
        this.url = url;
    }
}
