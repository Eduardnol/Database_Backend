package com.example.database_user.services;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.database_user.configuration.BaseTest;
import com.example.database_user.model.dto.Persona.PersonaDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.time.LocalDate;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
public class PersonaControllerTest extends BaseTest {

  private static final String BASE = "http://localhost:8080/api/v1/people";
  private static final String ALL_PEOPLE = BASE + "/allpeople";
  private static final String GET_BY_ID = BASE + "/getbyid/{id}";
  private static final String SEARCH = BASE + "/search/{name}";
  private static final String DATE_RANGE = BASE + "/daterange/{initial}/{final}";
  private static final String INSERT_NEW = BASE + "/insertnew";
  private static final String DELETE_BY_ID = BASE + "/deletebyid/{id}";
  private static final String UPDATE = BASE + "/update";
  private static final String SORT = BASE + "/sort";
  @Autowired
  private MockMvc mockMvc;

  @Test
  public void fetchAllPeople_returnsOkWhenCalledWithDefaultParameters() throws Exception {
    MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(ALL_PEOPLE)
            .contentType(MediaType.APPLICATION_JSON)
            .header("Authorization", "Bearer " + token))
        .andExpect(status().isOk())
        .andReturn();

    // Add assertions to check the response content if needed
  }

  @Test
  public void getById_returnsNotFoundWhenIdDoesNotExist() throws Exception {
    MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(GET_BY_ID, "nonexistent")
            .contentType(MediaType.APPLICATION_JSON)
            .header("Authorization", "Bearer " + token))
        .andExpect(status().isNotFound())
        .andReturn();

    // Add assertions to check the response content if needed
  }

  @Test
  public void fetchPeopleByName_returnsNotFoundWhenNameDoesNotExist() throws Exception {
    MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(GET_BY_ID, "nonexistent")
            .contentType(MediaType.APPLICATION_JSON)
            .header("Authorization", "Bearer " + token))
        .andExpect(status().isNotFound())
        .andReturn();

    // Add assertions to check the response content if needed
  }

  @Test
  public void insertNewUser_returnsBadRequestWhenUserIsNull() throws Exception {
    MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(INSERT_NEW)
            .contentType(MediaType.APPLICATION_JSON)
            .header("Authorization", "Bearer " + token))
        .andExpect(status().isBadRequest())
        .andReturn();

    // Add assertions to check the response content if needed
  }

  @Test
  @Order(1)
  public void insertNewUser_ok() throws Exception {
    PersonaDTO personaDTO = PersonaDTO.builder()
        .nombre("nombre")
        .apellido("apellido")
        .apellido2("apellido2")
        .email("email")
        .birthday(LocalDate.now())
        .saint(LocalDate.now())
        .dni("dni")
        .build();

    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.registerModule(new JavaTimeModule())
        .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

    MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(INSERT_NEW)
            .content(objectMapper.writeValueAsString(personaDTO))
            .contentType(MediaType.APPLICATION_JSON)
            .header("Authorization", "Bearer " + token))
        .andExpect(status().isCreated())
        .andReturn();

    // Add assertions to check the response content if needed
  }

  @Test
  public void deleteUser_returnsNotFoundWhenIdDoesNotExist() throws Exception {
    MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete(DELETE_BY_ID, "nonexistent")
            .param("id", "nonexistent")
            .contentType(MediaType.APPLICATION_JSON)
            .header("Authorization", "Bearer " + token))
        .andExpect(status().isNotFound())
        .andReturn();

    // Add assertions to check the response content if needed
  }

  @Test
  public void updateExisting_returnsBadRequestWhenUserIsNull() throws Exception {
    MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put(UPDATE)
            .contentType(MediaType.APPLICATION_JSON)
            .header("Authorization", "Bearer " + token))
        .andExpect(status().isBadRequest())
        .andReturn();

    // Add assertions to check the response content if needed
  }

  @Test
  public void sortPeopleByName_returnsBadRequestWhenDirectionIsInvalid() throws Exception {
    MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(SORT)
            .param("field", "name")
            .param("direction", "invalid")
            .contentType(MediaType.APPLICATION_JSON)
            .header("Authorization", "Bearer " + token))
        .andExpect(status().isBadRequest())
        .andReturn();

    // Add assertions to check the response content if needed
  }

  @Test
  public void sortPeopleByName_returnsBadRequestWhenFieldIsInvalid() throws Exception {
    MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(SORT)
            .param("field", "invalid")
            .param("direction", "asc")
            .contentType(MediaType.APPLICATION_JSON)
            .header("Authorization", "Bearer " + token))
        .andExpect(status().isBadRequest())
        .andReturn();

    // Add assertions to check the response content if needed
  }
}
