package com.example.database_user.dtos.Persona;

import jakarta.validation.constraints.Pattern;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.format.annotation.DateTimeFormat;

@AllArgsConstructor
@Builder
public class PersonaSacraments {

  @Pattern(regexp = "^(bautismo|confirmación|eucaristía|penitencia|unción|orden|matrimonio|)$")
  private String name;
  private String temple;
  @DateTimeFormat
  private LocalDate date;
}
