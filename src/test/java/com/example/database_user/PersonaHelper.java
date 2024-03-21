package com.example.database_user;

import com.example.database_user.model.dto.Persona.PersonaDTO;
import java.time.LocalDate;

public class PersonaHelper {

  public static PersonaDTO createPersona1() {
    PersonaDTO personaDTO = PersonaDTO.builder()
        .nombre("nombre")
        .apellido("apellido")
        .apellido2("apellido2")
        .email("email")
        .birthday(LocalDate.now())
        .saint(LocalDate.now())
        .dni("dni")
        .build();
    return personaDTO;
  }
  public static PersonaDTO UpdatePersona1() {
    PersonaDTO personaDTO = PersonaDTO.builder()
        .nombre("Nombre Update")
        .apellido("Apellido Update")
        .apellido2("Apellido2 Update")
        .email("Email update")
        .birthday(LocalDate.now())
        .saint(LocalDate.now())
        .dni("DNI UPDATE")
        .build();
    return personaDTO;
  }

}
