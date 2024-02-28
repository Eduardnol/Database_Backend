package com.example.database_user.controllers.impl;

import com.example.database_user.controllers.api.CustomTagManagerAPI;
import com.example.database_user.model.dto.Custom.CustomFieldsDTO;
import com.example.database_user.model.dto.Custom.CustomTagDTO;
import com.example.database_user.model.dto.Reponses.MainResponse;
import com.example.database_user.model.mapper.CustomFieldsMapper;
import com.example.database_user.repositories.CustomFieldsRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class CustomTagManagerController implements CustomTagManagerAPI {

  private CustomFieldsRepository customFieldsRepository;
  private CustomFieldsMapper customFieldsMapper;

  @Override
  public ResponseEntity<MainResponse> createCustomTag(String tagName) {
    return null;
  }

  @Override
  public ResponseEntity<MainResponse> deleteCustomTag(String tagId) {
    return null;
  }

  @Override
  public ResponseEntity<MainResponse> getAllCustomTags() {
    List<CustomFieldsDTO> customTagDTOS = customFieldsRepository.getAll()
        .stream()
        .map(customFieldsMapper::toDTO)
        .collect(Collectors.toList());
    if (customTagDTOS.isEmpty()) {
      return ResponseEntity.ok(
          new MainResponse("No custom tags found", HttpStatus.NO_CONTENT, null));
    } else {
      return ResponseEntity.ok(
          new MainResponse("Custom tags found", HttpStatus.OK, customTagDTOS));
    }
  }

  @Override
  public ResponseEntity<MainResponse> getCustomTagById(String tagId) {
    CustomTagDTO customTagDTOS = null;
    if (customTagDTOS == null) {
      return ResponseEntity.ok(
          new MainResponse("No custom tags found", HttpStatus.NO_CONTENT, null));
    } else {
      return ResponseEntity.ok(
          new MainResponse("Custom tags found", HttpStatus.OK, customTagDTOS));

    }
  }

  @Override
  public ResponseEntity<MainResponse> updateCustomTag(String tagId, String tagName) {
    return null;
  }
}
