package com.example.database_user.controllers.dto;

import com.example.database_user.controllers.dto.Persona.PersonaDTO;
import java.util.List;
import lombok.Data;

@Data
public class Stats {

    private int totalUsers;
  private List<PersonaDTO> todayBirthday;
  private List<PersonaDTO> todaySaint;
}
