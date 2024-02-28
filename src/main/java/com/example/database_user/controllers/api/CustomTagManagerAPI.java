package com.example.database_user.controllers.api;

import com.example.database_user.model.dto.Reponses.MainResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "CustomTagManager", description = "Manage custom tags")
@RestController
@RequestMapping("/api/v1/custom-tag")
public interface CustomTagManagerAPI {

  @Operation(summary = "Create a new custom tag")
  @PostMapping("/create")
  ResponseEntity<MainResponse> createCustomTag(String tagName);

  @Operation(summary = "Delete a custom tag")
  @DeleteMapping("/delete/{tagId}")
  ResponseEntity<MainResponse> deleteCustomTag(@PathVariable String tagId);

  @Operation(summary = "Get all custom tags")
  @GetMapping("/get-all")
  ResponseEntity<MainResponse> getAllCustomTags();

  @Operation(summary = "Get a custom tag by id")
  @GetMapping("/get-by-id/{tagId}")
  ResponseEntity<MainResponse> getCustomTagById(@PathVariable String tagId);

  @Operation(summary = "Update a custom tag")
  @PutMapping("/update/{tagId}")
  ResponseEntity<MainResponse> updateCustomTag(@PathVariable String tagId,
      @RequestBody String tagName);

}