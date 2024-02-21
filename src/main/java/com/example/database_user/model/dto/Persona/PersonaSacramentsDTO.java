package com.example.database_user.model.dto.Persona;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class PersonaSacramentsDTO {
  private String name;
  private String temple;
  private LocalDate date;
}
