package com.example.database_user.Model.Common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.UUID;

@Data
public class FileStorage {
    String originalName;
    @JsonIgnore
    String newName;
    @JsonIgnore
    String location;


    public FileStorage() {

        this.originalName = "";
        this.newName = "";
        this.location = "";
    }


    public static String generateNewName() {

        return UUID.randomUUID().toString();
    }


    ;
}
