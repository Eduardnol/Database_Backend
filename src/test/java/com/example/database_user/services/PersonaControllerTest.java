package com.example.database_user.services;

import com.example.database_user.controllers.dto.Persona.PersonaDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PersonaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private static final String BASE = "http://localhost:8080/api/v1/people";
    private static final String ALL_PEOPLE = BASE + "/allpeople";
    private static final String GET_BY_ID = BASE + "/getbyid/{id}";
    private static final String SEARCH = BASE + "/search/{name}";
    private static final String DATE_RANGE = BASE + "/daterange/{initial}/{final}";
    private static final String INSERT_NEW = BASE + "/insertnew";
    private static final String DELETE_BY_ID = BASE + "/deletebyid/{id}";
    private static final String UPDATE = BASE + "/update";
    private static final String SORT = BASE + "/sort";

    @Test
    public void fetchAllPeople_returnsOkWhenCalledWithDefaultParameters() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(ALL_PEOPLE)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        // Add assertions to check the response content if needed
    }

    @Test
    public void getById_returnsNotFoundWhenIdDoesNotExist() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(GET_BY_ID, "nonexistent")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();

        // Add assertions to check the response content if needed
    }

    @Test
    public void fetchPeopleByName_returnsNotFoundWhenNameDoesNotExist() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(GET_BY_ID, "nonexistent")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();

        // Add assertions to check the response content if needed
    }

    @Test
    public void insertNewUser_returnsBadRequestWhenUserIsNull() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(INSERT_NEW)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn();

        // Add assertions to check the response content if needed
    }

    @Test
    public void deleteUser_returnsNotFoundWhenIdDoesNotExist() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete(DELETE_BY_ID, "nonexistent")
                .param("id", "nonexistent")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();

        // Add assertions to check the response content if needed
    }

    @Test
    public void updateExisting_returnsBadRequestWhenUserIsNull() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put(UPDATE)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn();

        // Add assertions to check the response content if needed
    }

    @Test
    public void sortPeopleByName_returnsBadRequestWhenDirectionIsInvalid() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(SORT)
                .param("field", "name")
                .param("direction", "invalid")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn();

        // Add assertions to check the response content if needed
    }

    @Test
    public void sortPeopleByName_returnsBadRequestWhenFieldIsInvalid() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(SORT)
                .param("field", "invalid")
                .param("direction", "asc")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn();

        // Add assertions to check the response content if needed
    }
}
