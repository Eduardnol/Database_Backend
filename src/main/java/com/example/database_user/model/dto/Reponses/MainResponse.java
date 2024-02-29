package com.example.database_user.model.dto.Reponses;

import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MainResponse extends BaseResponse {
    private Object details;
    public MainResponse(String message, HttpStatus status, Object details) {
        super(message, status, LocalDateTime.now());
        this.details = details;
    }
}
