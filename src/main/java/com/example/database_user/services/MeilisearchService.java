package com.example.database_user.services;

import com.example.database_user.controllers.dto.Persona.PersonaDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.meilisearch.sdk.Client;
import com.meilisearch.sdk.Config;
import com.meilisearch.sdk.Index;
import com.meilisearch.sdk.model.Settings;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class MeilisearchService {

  //Singleton
  private static MeilisearchService instance = null;
  private Index persons = null;
  private Client client = null;

  private Index movies = null;

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
      log.error(e.getMessage());
    }
    try {
      movies = client.index("movies");
      movies.addDocuments(moviesJson);

      client.createIndex("users", "id");
      client.index("users").addDocuments(usersJson);

      Settings settings = client.index("users").getSettings();
      settings.setFilterableAttributes(new String[]{"birthday", "saint"});
      settings.setSortableAttributes(new String[]{"birthday", "saint"});
      client.index("users").updateSettings(settings);

      persons = client.index("users");

    } catch (Exception e) {
      log.error(e.getMessage());
    }
  }

  public void addUserDocument(PersonaDTO personaDTO) {
    ObjectMapper mapper = JsonMapper.builder().addModule(new JavaTimeModule()).build();

    try {
      persons.addDocuments(mapper.writeValueAsString(personaDTO));
    } catch (Exception e) {
      log.error(e.getMessage());
    }

  }

  public void updateDocument(PersonaDTO personaDTO) {
    ObjectMapper mapper = JsonMapper.builder().addModule(new JavaTimeModule()).build();

    try {
      persons.updateDocuments(mapper.writeValueAsString(personaDTO));
    } catch (Exception e) {
      log.error(e.getMessage());
    }

  }

  public void deleteDocument(String id) {
    try {
      persons.deleteDocument(id);
    } catch (Exception e) {
      log.error(e.getMessage());
    }

  }


}
