package com.example.database_user.repositories.entity;

import com.example.database_user.controllers.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.Required;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
@Builder
@Data
public class AuthUserEntity {

  @Id
  @Required
  private String id;
  @Required
  private String name;
  @Required
  private String surname;
  @Required
  @Email(message = "The email address is invalid.", flags = {Pattern.Flag.CASE_INSENSITIVE})
  private String email;
  @Required
  private Role role;


  //Password
  @Required
  private String password;
  @Required
  private List<String> passwordHistory;
  @Required
  private LocalDateTime passwordUpdatedAt;

  //Login
  @Required
  private LocalDateTime createdAt;
  @Required
  private List<LocalDateTime> loginHistory;
  @Required
  private List<String> loginIpHistory;
  @Required
  private boolean isLocked;
}
