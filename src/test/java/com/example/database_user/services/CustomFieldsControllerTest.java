package com.example.database_user.services;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.database_user.controllers.impl.CustomFieldsController;
import com.example.database_user.model.dto.custom.CustomFieldsDTO;
import com.example.database_user.model.dto.custom.CustomTagDTO;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;


@SpringBootTest
@AutoConfigureMockMvc
public class CustomFieldsControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @InjectMocks
  private CustomFieldsController customFieldsController;

  @Mock
  private CustomFieldsService customFieldsService;

  @BeforeEach
  public void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void testCreateCustomTag() throws Exception {
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

    when(customFieldsService.createCustomTag(customFieldsDTO)).thenReturn(customFieldsDTO);

    mockMvc.perform(post("/api/v1/custom-tag/create")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(customFieldsDTO)))
        .andExpect(status().isCreated());
  }
}
