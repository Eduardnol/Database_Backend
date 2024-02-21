package com.example.database_user.configs.security.auth_messages;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationReset {

  @Email
  private String email;
  private String oldPassword;
  private String newPassword;
  private String confirmNewPassword;

}
