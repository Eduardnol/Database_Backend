package com.example.database_user.controllers.dto.Reponses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.gson.annotations.SerializedName;
import org.springframework.http.HttpStatus;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class FileStorageResponse extends MainResponse {

    @SerializedName("name")
    private String name;
    @SerializedName("url")
    private String url;


    public FileStorageResponse(String message, HttpStatus status, String name, String url) {

        super(message, status, new FileStorageResponse(name, url));

    }


    private FileStorageResponse(String name, String url) {

        super();
        this.name = name;
        this.url = url;
    }
}
