package com.example.database_user.Model.Common;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface FileStorageService {

    public void init();

    public void save(MultipartFile file, String userid);

    public Resource load(String filename, String userid);

    public void deleteAll();

    public void deleteOne(String filename, String userid);

    public Stream<Path>  loadFromId(String id);

    public Stream<Path> loadAll();

}