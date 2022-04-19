package com.example.database_user.Model.Common;

import com.example.database_user.controllers.FileStorageController;
import com.example.database_user.dtos.FileStorage;
import com.example.database_user.dtos.Persona.Persona;
import com.example.database_user.repositories.PersonaRepository;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

//TODO : Verificar el formato en que nos entregan los ficheros
@Service
@AllArgsConstructor
public class FilesStorageServiceImpl implements FileStorageService {
	private static final Logger logger = LogManager.getLogger(FilesStorageServiceImpl.class);
	private final Path root = Paths.get("uploads");
	private final PersonaRepository personaRepository;


	@Override
	public void init() {

		try {
			Files.createDirectory(root);
		} catch (IOException e) {

			logger.warn("Could not initialize folder for upload");
			throw new RuntimeException("Could not initialize folder for upload!");
		}
	}


	/**
	 * Uploads a file to the database and memory
	 *
	 * @param file   Multipart file to be uploaded
	 * @param userid Id of the folders file location
	 */
	@Override
	public String save(MultipartFile file, String userid) {

		if (isValidType(file)) {
			String url;
			try {
				Files.createDirectory(this.root);
			} catch (IOException e) {
				System.out.println(e);
				logger.info("Folder already exists");
			}

			try {
				Files.createDirectory(this.root.resolve(userid));
			} catch (IOException e) {
				logger.info("Folder already exists");
			}

			try {
				Path originalFile = this.root.resolve(userid + "/" + file.getOriginalFilename());
				Files.copy(file.getInputStream(), originalFile);
				logger.info("Copied file into system");

				url = MvcUriComponentsBuilder
						.fromMethodName(FileStorageController.class, "getFile", userid, originalFile.getFileName().toString()).build().toString();


				Optional<Persona> persona = personaRepository.findById(userid);
				persona.ifPresent(value -> {

					value.getFileStorage().add(new FileStorage(file.getOriginalFilename(), url));
					personaRepository.deleteById(userid);
					personaRepository.insert(value);

					logger.info("Updated person on the database");


				});

			} catch (FileAlreadyExistsException e) {
				System.out.println("File already exists");
				logger.warn(e.getMessage());
				throw new RuntimeException("File Already Exists");
			} catch (IOException e) {
				logger.warn("Could not store the file " + Arrays.toString(e.getStackTrace()));
				System.out.println(e);
				throw new RuntimeException("Could not store the file.");
			}
			return url;
		} else {
			throw new RuntimeException("File is not a valid format");
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

				logger.warn("The actual file could not be read");
				throw new RuntimeException("Could not read the file!");

			}
		} catch (MalformedURLException e) {

			logger.warn("Malformed url exception " + e.getMessage());
			throw new RuntimeException("Error: " + e.getMessage());
		}
	}


	@Override
	public void deleteAll() {

		FileSystemUtils.deleteRecursively(root.toFile());
	}


	/**
	 * Deletes an specific file from the database and memory
	 *
	 * @param filename Name of the file to be deleted
	 * @param userid   Id of the folders file location
	 */
	@Override
	public void deleteOne(String filename, String userid) {
		//TODO delete from the database
		FileSystemUtils.deleteRecursively(root.resolve(userid + "/" + filename).toFile());

		Optional<Persona> persona = personaRepository.findById(userid);

		String url = MvcUriComponentsBuilder
				.fromMethodName(FileStorageController.class, "getFile", userid, filename).build().toString();


		persona.ifPresent(value -> {
			value.getFileStorage().remove(new FileStorage(filename, url));
			personaRepository.deleteById(userid);
			personaRepository.insert(persona.get());
			logger.info("Deleted file of person on the database");
		});


	}


	@Override
	public Stream<Path> loadAll() {

		try {
			return Files.walk(this.root, 1).filter(path -> !path.equals(this.root)).map(this.root::relativize);
		} catch (IOException e) {
			throw new RuntimeException("Could not load the files!");
		}
	}


	@Override
	public Stream<Path> loadFromId(String id) {

		try {
			return Files.walk(Path.of(this.root + "/" + id), 1).filter(path ->
					!path.equals(Path.of(this.root + "/" + id))).map(Path.of(this.root + "/" + id)::relativize);
		} catch (IOException e) {
			throw new RuntimeException("Could not load the files!");
		}
	}


	public boolean isValidType(MultipartFile file) {

		String content = file.getContentType();
		final List<String> contentTypes = Arrays.asList("image/png", "image/jpeg", "image/gif", "application/pdf");

		return contentTypes.contains(content);
	}
}
