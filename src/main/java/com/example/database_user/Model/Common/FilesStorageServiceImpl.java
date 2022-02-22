package com.example.database_user.Model.Common;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Service
public class FilesStorageServiceImpl implements FileStorageService {
    private final Path root = Paths.get("uploads");


    @Override
    public void init() {

        try {
            Files.createDirectory(root);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize folder for upload!");
        }
    }


    @Override
    public void save(MultipartFile file, String userid) {


        try {
            Files.createDirectory(this.root.resolve(userid));
        } catch (IOException e) {
            System.out.println("Folder " + userid + "already exists");
        }

        try {
            Files.copy(file.getInputStream(), this.root.resolve(userid + "/" + file.getOriginalFilename()));
        } catch (FileAlreadyExistsException e) {
            System.out.println("File already exists");
            throw new RuntimeException("File Already Exists");
        } catch (IOException e) {
            throw new RuntimeException("Could not store the file.");
        }
    }


    @Override
    public Resource load(String filename, String userid) {

        try {
            Path file = root.resolve(userid + "/" + filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }


    @Override
    public void deleteAll() {

        FileSystemUtils.deleteRecursively(root.toFile());
    }


    @Override
    public Stream<Path> loadAll() {

        try {
            return Files.walk(this.root, 1).filter(path -> !path.equals(this.root)).map(this.root::relativize);
        } catch (IOException e) {
            throw new RuntimeException("Could not load the files!");
        }
    }
}
