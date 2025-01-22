package com.generation.opticvbeckend.controllers;

import com.generation.opticvbeckend.model.entities.UploadedFile;
import com.generation.opticvbeckend.model.repositories.UploadedFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/files")
@CrossOrigin(origins = "http://localhost:4200")
public class FileUploadController {

    @Value("${file.upload-dir}")
    private String fileUploadDir;

    @Autowired
    private UploadedFileRepository fileRepository;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {

        try {
            // Nome del file originale
            String fileName = file.getOriginalFilename();
            UploadedFile file1 = new UploadedFile();
            file1.setFileName(fileName);
            file1.setFilePath(Paths.get(fileUploadDir, fileName).toAbsolutePath().toString());
            // Percorso personalizzato in cui salvare il file
            String uploadDir = "C:/uploads/";
            File directory = new File(uploadDir);
            if (!directory.exists()) {
                directory.mkdirs(); // Crea la cartella se non esiste
            }

            // Salva il file
            File destination = new File(uploadDir + fileName);
            file.transferTo(destination);

            return ResponseEntity.ok("File uploaded successfully: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading file");
        }

    }

}
