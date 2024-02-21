package com.example.database_user.configs.properties;

import jdk.jfr.Name;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@Configuration
@ConfigurationProperties("dbsi.jwt")
@Getter
@Setter
@Validated
public class JwtProperties {
  @NonNull
  private String key;

  @Name("expirationHours")
  private int expirationHours;

}
