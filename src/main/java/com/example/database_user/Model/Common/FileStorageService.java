package com.example.database_user.Model.Common;

import java.nio.file.Path;
import java.util.stream.Stream;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {

    void init();

    String save(MultipartFile file, String userid);

    Resource load(String filename, String userid);

    void deleteAll();

    void deleteOne(String filename, String userid);

    Stream<Path> loadFromId(String id);

    Stream<Path> loadAll();

}