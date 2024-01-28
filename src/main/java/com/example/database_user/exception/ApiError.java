package com.example.database_user.exception;

import java.time.LocalDateTime;
import lombok.Data;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.Required;

@Data
public class ApiError {

  @Required
  private Integer errorCode;
  @Required
  private String errorMessage;
  @Required
  private LocalDateTime timestamp;

}
