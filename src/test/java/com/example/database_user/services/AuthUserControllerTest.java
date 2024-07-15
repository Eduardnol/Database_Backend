package com.example.database_user.services;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.database_user.configs.security.auth_messages.AuthenticationRequest;
import com.example.database_user.configs.security.auth_messages.AuthenticationReset;
import com.example.database_user.configs.security.auth_messages.RegisterRequest;
import com.example.database_user.configuration.BaseTest;
import com.fasterxml.jackson.databind.ObjectMapper;
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
public class AuthUserControllerTest extends BaseTest {

  private static final String BASE = "http://localhost:8080/api/v1/auth";
  private static final String REGISTER = BASE + "/register";
  private static final String AUTHENTICATE = BASE + "/authenticate";
  private static final String RESET_PASSWORD = BASE + "/change-password";
  @Autowired
  private MockMvc mockMvc;

  @Test
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
    authenticationRequest.setEmail("test0@example.com");
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

  @Test
  public void resetPassword_returnsOkWhenCalledWithValidParameters() throws Exception {
    AuthenticationReset authenticationReset = new AuthenticationReset();
    authenticationReset.setEmail("test0@example.com");
    authenticationReset.setOldPassword("testPassword");
    authenticationReset.setNewPassword("newPassword");
    authenticationReset.setConfirmNewPassword("newPassword");

    ObjectMapper objectMapper = new ObjectMapper();
    String authenticationResetJson = objectMapper.writeValueAsString(authenticationReset);

    MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(RESET_PASSWORD)
            .contentType(MediaType.APPLICATION_JSON)
            .content(authenticationResetJson))
        .andExpect(status().isOk())
        .andReturn();
  }
}
