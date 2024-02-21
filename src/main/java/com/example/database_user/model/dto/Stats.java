package com.example.database_user.model.dto;

import com.example.database_user.model.dto.Persona.PersonaDTO;
import java.util.List;
import lombok.Data;

@Data
public class Stats {

  private int totalUsers;
  private List<PersonaDTO> todayBirthday;
  private List<PersonaDTO> todaySaint;
}
