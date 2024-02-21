package com.example.database_user.repositories.entity;

import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

public class PersonaSacramentsEntity {

  // @Pattern(regexp = "^(bautismo|confirmación|eucaristía|penitencia|unción|orden|matrimonio|)$")
  private String name;
  private String temple;
  @DateTimeFormat
  private LocalDate date;
}
