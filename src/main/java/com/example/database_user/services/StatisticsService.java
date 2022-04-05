package com.example.database_user.services;

import com.example.database_user.repositories.StatisticsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class StatisticsService {
	private final StatisticsRepository statisticsRepository;


	/**
	 * Calculates all the people inside the database
	 * @return
	 */
	public Integer countAllPeople() {

		return Math.toIntExact(statisticsRepository.count());

	}
}
