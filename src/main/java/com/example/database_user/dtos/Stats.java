package com.example.database_user.dtos;

import com.example.database_user.dtos.Persona.Persona;
import java.util.List;
import lombok.Data;

@Data
public class Stats {

    private int totalUsers;
    private List<Persona> todayBirthday;
    private List<Persona> todaySaint;
}
