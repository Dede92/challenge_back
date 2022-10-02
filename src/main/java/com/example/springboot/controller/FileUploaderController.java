package com.example.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.springboot.model.Message;
import com.example.springboot.model.MessageIcon;
import com.example.springboot.model.ResponseFile;
import com.example.springboot.model.ResponseMessage;
import com.example.springboot.service.FileStorageService;
import com.example.springboot.service.MessageService;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
public class FileUploaderController {

    @Autowired
    private FileStorageService storageService;

    @Autowired
    private MessageService messageService;

    @PostMapping("/upload/{id}")
    public ResponseEntity<ResponseMessage> uploadFile(@PathVariable(value = "id") Long messageId,
            @RequestParam("file") MultipartFile file) {
        String message = "";
        try {
            Message message_db = messageService.findById(messageId);

            storageService.store(file, message_db);

            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    @GetMapping("/files")
    public ResponseEntity<List<ResponseFile>> getListFiles() {
        List<ResponseFile> files = storageService.getAllFiles().map(dbFile -> {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/files/").path(dbFile.getId()
                            .toString())
                    .toUriString();

            return new ResponseFile(
                    dbFile.getFileName(),
                    fileDownloadUri,
                    dbFile.getFileType(),
                    dbFile.getData().length);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(files);
    }

    @GetMapping("/files/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable Long id) {
        MessageIcon messageIcon = storageService.getFile(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + messageIcon.getFileName() + "\"")
                .body(messageIcon.getData());
    }

}