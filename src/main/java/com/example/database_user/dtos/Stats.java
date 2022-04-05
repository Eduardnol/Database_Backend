package com.example.database_user.dtos;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
public class Stats {
	private int totalUsers;
	private List<Persona> todayBirthday;
	private List<Persona> todaySaint;
}
