package com.example.database_user.controllers.api;

import com.example.database_user.model.dto.Reponses.MainResponse;
import com.example.database_user.model.dto.custom.CustomFieldsDTO;
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
  ResponseEntity<MainResponse> createCustomTag(@RequestBody @Valid CustomFieldsDTO customFieldsDTO);

  @Operation(summary = "Delete a custom tag by its id")
  @DeleteMapping("/delete/{tagId}")
  ResponseEntity<MainResponse> deleteCustomTag(@PathVariable String tagId);

  @Operation(summary = "Get all custom tags")
  @GetMapping("/get-all")
  ResponseEntity<MainResponse> getAllCustomTags();

  @Operation(summary = "Get a custom tag by id")
  @GetMapping("/get-by-id/{tagId}")
  ResponseEntity<MainResponse> getCustomTagById(@PathVariable String tagId);

  @Operation(summary = "Update a custom tag by id and request body tagname")
  @PutMapping("/update/{tagId}")
  ResponseEntity<MainResponse> updateCustomTag(@PathVariable String tagId,
      @RequestBody String tagName);

}
