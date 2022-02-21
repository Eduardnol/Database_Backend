package com.example.database_user.Model.Common;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class FileStorageService {
    private final FileStorageRepository fileStorageRepository;


    public List<FileStorage> fetchAllFiles() {

        return fileStorageRepository.findAll();

    }
}
