package com.example.database_user.controllers.impl;

import com.example.database_user.controllers.api.EmmausAPI;
import com.example.database_user.model.dto.discipulado.EmausDTO;
import com.example.database_user.services.EmmausService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class EmmausController implements EmmausAPI {

  private final EmmausService emmausService;

  public ResponseEntity<List<EmausDTO>> getAllEmmaus() {
    //Do not query the people inside
    return null;
  }

}
