package com.example.database_user.Model.Common;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@AllArgsConstructor
@Service
public class FileStorageService {
    private final FileStorageRepository fileStorageRepository;
    private final StorageService storageService;


    public List<FileStorage> fetchAllFiles() {

        return fileStorageRepository.findAll();

    }


    public void uploadFile(MultipartFile file) {

        storageService.store(file);
    }
}
