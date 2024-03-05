package com.example.database_user.services;

import com.example.database_user.controllers.impl.CustomFieldsController;
import com.example.database_user.model.dto.custom.CustomFieldsDTO;
import com.example.database_user.model.dto.Reponses.MainResponse;
import com.example.database_user.model.dto.custom.CustomTagDTO;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class CustomFieldsControllerTest {

  @InjectMocks
  private CustomFieldsController customFieldsController;

  @Mock
  private CustomFieldsService customFieldsService;

  @BeforeEach
  public void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void testCreateCustomTag() {
    CustomTagDTO customTagDTO = CustomTagDTO.builder()
        .id("tagId")
        .tagName("tagName")
        .typeOfData("typeOfData")
        .data("data")
        .build();
    List<CustomTagDTO> customTagDTOList = new ArrayList<>();
    customTagDTOList.add(customTagDTO);
    CustomFieldsDTO customFieldsDTO = CustomFieldsDTO.builder()
        .id("tagId")
        .name("tagName")
        .customTagDTOS(customTagDTOList)
        .build();

    MainResponse expectedResponse = new MainResponse("Custom tag created", HttpStatus.CREATED,
        customFieldsDTO);
    when(customFieldsService.createCustomTag(customFieldsDTO)).thenReturn(customFieldsDTO);

    ResponseEntity<MainResponse> responseEntity = customFieldsController.createCustomTag(
        customFieldsDTO);

    assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
  }
}
