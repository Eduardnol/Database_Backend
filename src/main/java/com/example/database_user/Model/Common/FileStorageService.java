package com.example.database_user.Model.Common;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface FileStorageService {

    void init();

    String save(MultipartFile file, String userid);

    Resource load(String filename, String userid);

    void deleteAll();

    void deleteOne(String filename, String userid);

    Stream<Path> loadFromId(String id);

    Stream<Path> loadAll();

}