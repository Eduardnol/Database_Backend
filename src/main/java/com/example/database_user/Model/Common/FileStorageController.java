package com.example.database_user.Model.Common;

import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "api/v1/files")
@AllArgsConstructor
public class FileStorageController {

    private final FileStorageService storageService;


    /**
     * {@code POST  /genres} : Create a new genre.
     *
     * @param userid identificator of the owner of the file to be uploaded
     * @param file   File to be uploaded
     * @return Response entity with all the file uploader
     */

    @PostMapping("/upload/{userid}")
    public ResponseEntity<ResponseMessage> uploadFile(@PathVariable String userid, @RequestParam("file") MultipartFile file) {

        String message = "";
        try {
            storageService.save(file, userid);
            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
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
                    .fromMethodName(FileStorageController.class, "getFile", path.getFileName().toString()).build().toString();
            return new FileStorage(filename, url);
        }).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
    }


    /**
     * Gets an specific file an specific user
     *
     * @param userid   User owner of the file
     * @param filename Name of the file to be downloaded
     * @return Response entity of with the result of the operation
     */
    @GetMapping("{userid}/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getFile(@PathVariable String userid, @PathVariable String filename) {

        Resource file = storageService.load(filename, userid);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
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
    public ResponseEntity<ResponseMessage> deleteFile(@PathVariable String userid, @PathVariable String filename) {

        String message = "";
        storageService.deleteOne(filename, userid);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
    }


    /**
     * Given an id, retrieve all the files corresponding to that id stored in filesystem
     * @param id Identificator of the owner of the file
     * @return Response entity with the list of the files and it's url access
     */

    @GetMapping("{id}")
    @ResponseBody
    public ResponseEntity<List<FileStorage>> getIdFiles(@PathVariable String id) {

        List<FileStorage> fileInfos = storageService.loadFromId(id).map(path -> {
            String filename = path.getFileName().toString();
            String url = MvcUriComponentsBuilder
                    .fromMethodName(FileStorageController.class, "getFile", id, path.getFileName().toString()).build().toString();
            return new FileStorage(filename, url);
        }).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
    }


}
