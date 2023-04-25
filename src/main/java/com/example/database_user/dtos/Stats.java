package com.example.database_user.dtos;

import com.example.database_user.dtos.Persona.Persona;
import lombok.Data;

import java.util.List;

@Data
public class Stats {
    private int totalUsers;
    private List<Persona> todayBirthday;
    private List<Persona> todaySaint;
}
