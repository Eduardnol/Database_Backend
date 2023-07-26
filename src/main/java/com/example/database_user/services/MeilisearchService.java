package com.example.database_user.services;

import com.example.database_user.dtos.Persona.Persona;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.meilisearch.sdk.Client;
import com.meilisearch.sdk.Config;
import com.meilisearch.sdk.Index;
import com.meilisearch.sdk.Settings;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


public class MeilisearchService {

    //Singleton
    private static MeilisearchService instance = null;
    private Index index1 = null;
    private Client client = null;

    public static MeilisearchService getInstance() {
        if (instance == null) {
            instance = new MeilisearchService();
            instance.startService();
        }
        return instance;
    }

    private void startService() {

        client = new Client(new Config("http://127.0.0.1:7720", "masterKey"));
        importDocuments();


    }

    private void importDocuments() {
        Path fileName = Path.of("movies.json");
        Path fileNameUsers = Path.of("users_meili.json");
        String moviesJson = null;
        String usersJson = null;
        try {
            moviesJson = Files.readString(fileName);
            usersJson = Files.readString(fileNameUsers);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
//      index = client.index("movies");
//      index.addDocuments(moviesJson);
            client.createIndex("users");

            Settings settings = client.index("users").getSettings();
            settings.setFilterableAttributes(new String[]{"birthday", "saint", "sacraments"});

            client.index("users").updateSettings(settings);
            client.index("users").addDocuments(usersJson);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void addUserDocument(Persona persona) {
        ObjectMapper mapper = JsonMapper.builder().addModule(new JavaTimeModule()).build();

        try {
            index1.addDocuments(mapper.writeValueAsString(persona));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public void updateDocument(Persona persona) {
        ObjectMapper mapper = JsonMapper.builder().addModule(new JavaTimeModule()).build();

        try {
            index1.updateDocuments(mapper.writeValueAsString(persona));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public void deleteDocument(String id) {
        try {
            index1.deleteDocument(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


}
