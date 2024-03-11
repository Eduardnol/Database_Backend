package com.example.database_user.configs.properties;

import jdk.jfr.Name;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("dbsi.jwt")
@Getter
@Setter
public class JwtProperties {

  @NonNull
  private String key;

  @Name("expirationHours")
  private int expirationHours;
}
