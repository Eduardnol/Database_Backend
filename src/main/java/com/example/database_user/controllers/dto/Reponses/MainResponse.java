package com.example.database_user.controllers.dto.Reponses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MainResponse {
    private String message;
    private HttpStatus status;
    private Object details;
}
