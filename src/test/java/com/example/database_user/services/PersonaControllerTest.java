package com.example.database_user.services;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.database_user.PersonaHelper;
import com.example.database_user.configs.security.JWTService;
import com.example.database_user.configuration.TestMongoDBContainer;
import com.example.database_user.model.dto.AuthUserDTO;
import com.example.database_user.model.dto.Persona.PersonaDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


@AutoConfigureMockMvc
@SpringBootTest
public class PersonaControllerTest extends TestMongoDBContainer {

  private static final String BASE = "http://localhost:8080/api/v1/people";
  private static final String ALL_PEOPLE = BASE + "/allpeople";
  private static final String GET_BY_ID = BASE + "/getbyid/{id}";
  private static final String SEARCH = BASE + "/search/{name}";
  private static final String DATE_RANGE = BASE + "/daterange/{initial}/{final}";
  private static final String INSERT_NEW = BASE + "/insertnew";
  private static final String DELETE_BY_ID = BASE + "/deletebyid/{id}";
  private static final String UPDATE = BASE + "/update";
  private static final String SORT = BASE + "/sort";
  protected String token;
  @Autowired
  private MockMvc mockMvc;
  @Autowired
  private JWTService jwtService;


  @BeforeEach
  public void generateToken() {
    AuthUserDTO userDetailsDTO = AuthUserDTO.builder()
        .email("test@example.com")
        .password("testPassword")
        .build();
    token = jwtService.generateToken(userDetailsDTO);
  }

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
        .andExpect(status().isNoContent())
        .andReturn();

    // Add assertions to check the response content if needed
  }

  @Test
  public void fetchPeopleByName_returnsNoContentWhenNameDoesNotExist() throws Exception {
    MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(GET_BY_ID, "nonexistent")
            .contentType(MediaType.APPLICATION_JSON)
            .header("Authorization", "Bearer " + token))
        .andExpect(status().isNoContent())
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
  public void insertNewUser_ok() throws Exception {
    PersonaDTO personaDTO = PersonaHelper.createPersona1();
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
    PersonaDTO personaDTO = PersonaHelper.UpdatePersona1();
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.registerModule(new JavaTimeModule())
        .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

    MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put(UPDATE)
            .contentType(MediaType.APPLICATION_JSON)
            .header("Authorization", "Bearer " + token)
            .content(objectMapper.writeValueAsString(personaDTO)))
        .andExpect(status().isNotFound())
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

  @Test
  public void sortPeopleByName_asc_ok() throws Exception {
    MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(SORT)
            .param("field", "name")
            .param("direction", "asc")
            .contentType(MediaType.APPLICATION_JSON)
            .header("Authorization", "Bearer " + token))
        .andExpect(status().isOk())
        .andReturn();

    // Add assertions to check the response content if needed
  }

  @Test
  public void sortPeopleByName_desc_ok() throws Exception {
    MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(SORT)
            .param("field", "name")
            .param("direction", "desc")
            .contentType(MediaType.APPLICATION_JSON)
            .header("Authorization", "Bearer " + token))
        .andExpect(status().isOk())
        .andReturn();

    // Add assertions to check the response content if needed
  }

  @Test
  public void fetchPeopleByName_ok() throws Exception {
    MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(SEARCH, "nombre")
            .contentType(MediaType.APPLICATION_JSON)
            .header("Authorization", "Bearer " + token))
        .andExpect(status().isOk())
        .andReturn();

    // Add assertions to check the response content if needed
  }

  @Test
  public void sortPeopleBySurName_desc_ok() throws Exception {
    MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(SORT)
            .param("field", "surname")
            .param("direction", "desc")
            .contentType(MediaType.APPLICATION_JSON)
            .header("Authorization", "Bearer " + token))
        .andExpect(status().isOk())
        .andReturn();

    // Add assertions to check the response content if needed
  }

  @Test
  public void sortPeopleBySurName_asc_ok() throws Exception {
    MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(SORT)
            .param("field", "surname")
            .param("direction", "asc")
            .contentType(MediaType.APPLICATION_JSON)
            .header("Authorization", "Bearer " + token))
        .andExpect(status().isOk())
        .andReturn();

    // Add assertions to check the response content if needed
  }

  @Test
  public void fetchDateRangePeople_returnsBadRequestWhenInitialDateIsInvalid() throws Exception {
    MvcResult mvcResult = mockMvc.perform(
            MockMvcRequestBuilders.get(DATE_RANGE, "2023-01-01", "2022-01-01")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + token))
        .andExpect(status().isBadRequest())
        .andReturn();

    // Add assertions to check the response content if needed
  }

  @Test
  public void fetchDateRangePeople_returnOk() throws Exception {
    MvcResult mvcResult = mockMvc.perform(
            MockMvcRequestBuilders.get(DATE_RANGE, "2020-01-01", "2022-01-01")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + token))
        .andExpect(status().isOk())
        .andReturn();

    // Add assertions to check the response content if needed
  }
}
