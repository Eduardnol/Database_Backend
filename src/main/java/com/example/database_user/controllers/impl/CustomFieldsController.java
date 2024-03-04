package com.example.database_user.controllers.impl;

import com.example.database_user.controllers.api.CustomFieldsAPI;
import com.example.database_user.model.dto.Reponses.MainResponse;
import com.example.database_user.model.dto.custom.CustomFieldsDTO;
import com.example.database_user.model.dto.custom.CustomTagDTO;
import com.example.database_user.services.CustomFieldsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomFieldsController implements CustomFieldsAPI {

  @Autowired
  private CustomFieldsService customFieldsService;

  @Override
  public ResponseEntity<MainResponse> createCustomField(CustomFieldsDTO customFieldsDTO) {
    return ResponseEntity.status(HttpStatus.CREATED).body(
        new MainResponse("Custom tag created", HttpStatus.CREATED,
            customFieldsService.createCustomField(customFieldsDTO)));
  }

  @Override
  public ResponseEntity<MainResponse> deleteCustomField(String fieldId) {
    return null;
  }

  @Override
  public ResponseEntity<MainResponse> getAllCustomFields() {
    return ResponseEntity.ok(
        new MainResponse("Custom fields found", HttpStatus.OK,
            customFieldsService.getAllCustomFields()));

  }

  @Override
  public ResponseEntity<MainResponse> getCustomFieldById(String fieldId) {
    CustomFieldsDTO customTagDTO = customFieldsService.getCustomFieldById(fieldId);
    return ResponseEntity.ok(
        new MainResponse("Custom fields found", HttpStatus.OK, customTagDTO));


  }

  @Override
  public ResponseEntity<MainResponse> updateCustomField(String FieldId, String fieldName) {
    return null;
  }

  @Override
  public ResponseEntity<MainResponse> addTagToCustomField(String fieldId, CustomTagDTO tagDTO) {
    return null;
  }

  @Override
  public ResponseEntity<MainResponse> deleteTagFromCustomField(String fieldId, String tagName) {
    return null;
  }
}
