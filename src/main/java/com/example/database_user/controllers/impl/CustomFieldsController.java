package com.example.database_user.controllers.impl;

import com.example.database_user.controllers.api.CustomFieldsAPI;
import com.example.database_user.model.dto.custom.CustomFieldsDTO;
import com.example.database_user.model.dto.Reponses.MainResponse;
import com.example.database_user.services.CustomFieldsService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomFieldsController implements CustomFieldsAPI {

  @Autowired
  private CustomFieldsService customFieldsService;

  @Override
  public ResponseEntity<MainResponse> createCustomTag(CustomFieldsDTO customFieldsDTO) {
    return ResponseEntity.status(HttpStatus.CREATED).body(
        new MainResponse("Custom tag created", HttpStatus.CREATED,
            customFieldsService.createCustomTag(customFieldsDTO)));
  }

  @Override
  public ResponseEntity<MainResponse> deleteCustomTag(String tagId) {
    return null;
  }

  @Override
  public ResponseEntity<MainResponse> getAllCustomTags() {
    return ResponseEntity.ok(
        new MainResponse("Custom tags found", HttpStatus.OK,
            customFieldsService.getAllCustomTags()));

  }

  @Override
  public ResponseEntity<MainResponse> getCustomTagById(String tagId) {
    CustomFieldsDTO customTagDTO = customFieldsService.getCustomTagById(tagId);
    return ResponseEntity.ok(
        new MainResponse("Custom tags found", HttpStatus.OK, customTagDTO));


  }

  @Override
  public ResponseEntity<MainResponse> updateCustomTag(String tagId, String tagName) {
    return null;
  }
}
