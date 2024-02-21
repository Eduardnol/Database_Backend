package com.example.database_user.controllers.api;

import com.example.database_user.model.dto.EmausDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Tag(name = "Emmaus", description = "Obtain all information relative to the Emmaus")
@RequestMapping(path = "api/v1/emmaus")
public interface EmmausAPI {
  @GetMapping("/allemmaus")
  public ResponseEntity<List<EmausDTO>> getAllEmmaus();


}
