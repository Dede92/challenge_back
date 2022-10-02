package com.example.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.example.springboot.model.Message;
import com.example.springboot.model.MessageIcon;
import com.example.springboot.repository.FileStorageRepository;

import java.io.IOException;
import java.util.stream.Stream;

@Service
public class FileStorageService {
    @Autowired
    private FileStorageRepository fileStorageRepository;

    public MessageIcon store(MultipartFile file, Message message) throws IOException {


        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        MessageIcon FileDB = new MessageIcon(fileName, file.getContentType(), file.getBytes(), message);

        return fileStorageRepository.save(FileDB);
    }

    public MessageIcon getFile(Long id) {
        return fileStorageRepository.findById(id).get();
    }

    public Stream<MessageIcon> getAllFiles() {
        return fileStorageRepository.findAll().stream();
    }
}
