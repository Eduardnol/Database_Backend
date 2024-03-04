package com.example.database_user.controllers.api;

import com.example.database_user.model.dto.Reponses.MainResponse;
import com.example.database_user.model.dto.custom.CustomFieldsDTO;
import com.example.database_user.model.dto.custom.CustomTagDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "CustomTagManager", description = "Manage custom tags")
@RequestMapping("/api/v1/custom-tag")
public interface CustomFieldsAPI {

  @Operation(summary = "Create a new custom tag")
  @PostMapping(value = "/create", produces = "application/json", consumes = "application/json")
  ResponseEntity<MainResponse> createCustomField(
      @RequestBody @Valid CustomFieldsDTO customFieldsDTO);

  @Operation(summary = "Delete a custom tag by its id")
  @DeleteMapping("/delete/{fieldId}")
  ResponseEntity<MainResponse> deleteCustomField(@PathVariable String fieldId);

  @Operation(summary = "Get all custom tags")
  @GetMapping("/get-all")
  ResponseEntity<MainResponse> getAllCustomFields();

  @Operation(summary = "Get a custom field by id")
  @GetMapping("/get-by-id/{field}")
  ResponseEntity<MainResponse> getCustomFieldById(@PathVariable String field);

  @Operation(summary = "Update a custom field")
  @PutMapping("/update/{fieldId}")
  ResponseEntity<MainResponse> updateCustomField(@PathVariable String fieldId,
      @RequestBody String fieldName);

  @Operation(summary = "Add a tag to a custom field")
  @PutMapping("/add-tag/{fieldId}")
  ResponseEntity<MainResponse> addTagToCustomField(@PathVariable String fieldId,
      @RequestBody CustomTagDTO tagDTO);

  @Operation(summary = "Delete a tag from a custom field")
  @DeleteMapping("/delete-tag/{fieldId}")
  ResponseEntity<MainResponse> deleteTagFromCustomField(@PathVariable String fieldId,
      @RequestBody String tagName);

}
