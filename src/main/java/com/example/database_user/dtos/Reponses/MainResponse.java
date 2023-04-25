package com.example.database_user.dtos.Reponses;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
public class MainResponse {

    private String message;
    private HttpStatus status;
    private Object details;

}
