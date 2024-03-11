package com.example.database_user.services;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.database_user.configuration.BaseTest;
import com.example.database_user.model.dto.custom.CustomFieldsDTO;
import com.example.database_user.model.dto.custom.CustomTagDTO;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
public class CustomFieldsControllerTest extends BaseTest {

  private static final String BASE = "http://localhost:8080/api/v1/people";
  private static final String CUSTOM_TAG = BASE + "/custom-tag";
  private static final String CREATE_CUSTOM_TAG = CUSTOM_TAG + "/create";
  private static final String DELETE_BY_ID = CUSTOM_TAG + "/delete/{tagId}";
  private static final String GET_ALL = CUSTOM_TAG + "/get-all";


  @Autowired
  private MockMvc mockMvc;

  @Mock
  private ObjectMapper objectMapper;

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

    mockMvc.perform(post(CREATE_CUSTOM_TAG)
            .contentType(MediaType.APPLICATION_JSON)
            .header("Authorization", "Bearer " + token)
            .content(objectMapper.writeValueAsString(customFieldsDTO)))
        .andExpect(status().isCreated());
  }

  @Test
  public void deleteCustomTagById() throws Exception {
    String tagId = "tagId";
    mockMvc.perform(delete(DELETE_BY_ID, tagId)
            .header("Authorization", "Bearer " + token))
        .andExpect(status().isOk());
  }

  @Test
  public void testGetAllCustomTags() throws Exception {
    mockMvc.perform(get(GET_ALL)
            .header("Authorization", "Bearer " + token))
        .andExpect(status().isOk());
  }

}
