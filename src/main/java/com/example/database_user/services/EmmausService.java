package com.example.database_user.services;

import com.example.database_user.repositories.EmmausRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class EmmausService {

    private final EmmausRepository emmausRepository;
}
