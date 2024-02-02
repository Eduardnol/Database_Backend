package com.example.database_user.services;

import com.example.database_user.configs.security.auth_messages.AuthenticationRequest;
import com.example.database_user.configs.security.auth_messages.RegisterRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Disabled;
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
public class AuthUserControllerTest {

  @Autowired
  private MockMvc mockMvc;

  private static final String BASE = "http://localhost:8080/api/v1/auth";
  private static final String REGISTER = BASE + "/register";
  private static final String AUTHENTICATE = BASE + "/authenticate";

  @Test
  @Disabled
  public void register_returnsBadRequestWhenCalledWithInvalidParameters() throws Exception {
    RegisterRequest registerRequest = new RegisterRequest();
    registerRequest.setEmail("invalidEmail");
    registerRequest.setPassword("testPassword");
    registerRequest.setName("testName");
    registerRequest.setSurname("testSurname");

    ObjectMapper objectMapper = new ObjectMapper();
    String registerRequestJson = objectMapper.writeValueAsString(registerRequest);

    MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(REGISTER)
            .contentType(MediaType.APPLICATION_JSON)
            .content(registerRequestJson))
        .andExpect(status().isBadRequest())
        .andReturn();

    // Add assertions to check the response content if needed
  }

  @Test
  public void register_returnsOkWhenCalledWithValidParameters() throws Exception {
    RegisterRequest registerRequest = new RegisterRequest();
    registerRequest.setEmail("test@example.com");
    registerRequest.setPassword("testPassword");
    registerRequest.setName("testName");
    registerRequest.setSurname("testSurname");

    ObjectMapper objectMapper = new ObjectMapper();
    String registerRequestJson = objectMapper.writeValueAsString(registerRequest);

    MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(REGISTER)
            .contentType(MediaType.APPLICATION_JSON)
            .content(registerRequestJson))
        .andExpect(status().isOk())
        .andReturn();

    // Add assertions to check the response content if needed
  }

  @Test
  public void authenticate_returnsOkWhenCalledWithValidParameters() throws Exception {
    AuthenticationRequest authenticationRequest = new AuthenticationRequest();
    authenticationRequest.setEmail("test@example.com");
    authenticationRequest.setPassword("testPassword");

    ObjectMapper objectMapper = new ObjectMapper();
    String authenticationRequestJson = objectMapper.writeValueAsString(authenticationRequest);

    MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(AUTHENTICATE)
            .contentType(MediaType.APPLICATION_JSON)
            .content(authenticationRequestJson))
        .andExpect(status().isOk())
        .andReturn();

    // Add assertions to check the response content if needed
  }

  @Test
  @Disabled
  public void authenticate_returnsBadRequestWhenCalledWithInvalidParameters() throws Exception {
    AuthenticationRequest authenticationRequest = new AuthenticationRequest();
    authenticationRequest.setEmail("invalidEmail");
    authenticationRequest.setPassword("testPassword");

    ObjectMapper objectMapper = new ObjectMapper();
    String authenticationRequestJson = objectMapper.writeValueAsString(authenticationRequest);

    MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(AUTHENTICATE)
            .contentType(MediaType.APPLICATION_JSON)
            .content(authenticationRequestJson))
        .andExpect(status().isBadRequest())
        .andReturn();

    // Add assertions to check the response content if needed
  }
}