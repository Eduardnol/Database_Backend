package com.example.database_user.controllers;

import com.example.database_user.model.FileStorageService;
import com.example.database_user.model.dto.FileStorage;
import com.example.database_user.model.dto.ResponseMessage;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

@RestController
@RequestMapping(path = "api/v1/files")
@AllArgsConstructor
public class FileStorageController {

    @Autowired
    private final FileStorageService storageService;


    /**
     * {@code POST  /genres} : Create a new genre.
     *
     * @param userid identificator of the owner of the file to be uploaded
     * @param file   File to be uploaded
     * @return Response entity with all the file uploader
     */

    @PostMapping("/upload/{userid}")
    public ResponseEntity<String> uploadFile(@PathVariable String userid,
            @RequestParam("file") MultipartFile file) {

//        final HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
//        String message = "";
//
//        if (!file.isEmpty()) {
//
//            try {
//                String url = storageService.save(file, userid);
//                FileStorageResponse fileStorage = new FileStorageResponse("File Uploaded Correctly",
//                        HttpStatus.OK,
//                        file.getOriginalFilename(),
//                        url);
//                //Make http headers as json and return it
//                return new ResponseEntity<>(message, httpHeaders, HttpStatus.OK);
//            } catch (Exception e) {
//                message = "Could not upload the file: " + e.getMessage();
//                return new ResponseEntity<>(gson.toJson(message), httpHeaders,
//                        HttpStatus.EXPECTATION_FAILED);
//            }
//        } else {
//            message = "Could not upload the file: It's empty";
//            return new ResponseEntity<>(gson.toJson(message), httpHeaders,
//                    HttpStatus.EXPECTATION_FAILED);
//
//        }
    return null;
    }


    /**
     * Lists all the files of the server (NOT WORKING)
     *
     * @return Returns the result of the query together with the listed files
     */
    @GetMapping("/allfiles")
    public ResponseEntity<List<FileStorage>> getListFiles() {

        List<FileStorage> fileInfos = storageService.loadAll().map(path -> {
            String filename = path.getFileName().toString();
            String url = MvcUriComponentsBuilder
                    .fromMethodName(FileStorageController.class, "getFile",
                            path.getFileName().toString())
                    .build()
                    .toString();
            return new FileStorage(filename, url);
        }).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
    }


    /**
     * Gets an specific file of an specific user
     *
     * @param userid   User owner of the file
     * @param filename Name of the file to be downloaded
     * @return Response entity of with the result of the operation
     */
    @GetMapping("{userid}/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getFileFromUserIdByName(@PathVariable String userid,
                                                           @PathVariable String filename) {

        Resource file = storageService.load(filename, userid);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }


    /**
     * Deletes a user's file
     *
     * @param userid   The owner of the file to be deleted
     * @param filename Name of the file to be deleted
     * @return Response entity with the result of the operation
     */
    @DeleteMapping("{userid}/{filename:.+}")
    @ResponseBody
    public ResponseEntity<ResponseMessage> deleteUserFile(@PathVariable String userid,
                                                          @PathVariable String filename) {

        String message = "";
        storageService.deleteOne(filename, userid);
        //TODO delete file from database
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
    }


    /**
     * Given an id, retrieve all the files corresponding to that id stored in filesystem
     *
     * @param id Identificator of the owner of the file
     * @return Response entity with the list of the files and it's url access
     */

    @GetMapping("{id}")
    @ResponseBody
    public ResponseEntity<List<FileStorage>> getFileById(@PathVariable String id) {

        List<FileStorage> fileInfos = storageService.loadFromId(id).map(path -> {
            String filename = path.getFileName().toString();
            String url = MvcUriComponentsBuilder
                    .fromMethodName(FileStorageController.class, "getFile", id,
                            path.getFileName().toString())
                    .build()
                    .toString();
            return new FileStorage(filename, url);
        }).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
    }


}
