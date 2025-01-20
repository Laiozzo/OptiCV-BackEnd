package com.generation.opticvbeckend.controllers;

import com.generation.opticvbeckend.model.entities.UploadedFile;
import com.generation.opticvbeckend.model.repositories.UploadedFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/files")
public class FileUploadController {

    @Value("${file.upload-dir}")
    private String fileUploadDir;

    @Autowired
    private UploadedFileRepository fileRepository;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        //Controllo se il file è un PDF
        if(!file.getContentType().equals("application/pdf")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Only PDF files are supported");
        }

        try{
            Path path = Paths.get(fileUploadDir);
            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }

            //Salva il file :
            String filePath = fileUploadDir + File.separator + file.getOriginalFilename();
            file.transferTo(new File(filePath));

            // Salva i metadati nel database
            UploadedFile uploadedFile = new UploadedFile();
            uploadedFile.setFileName(file.getOriginalFilename());
            uploadedFile.setFilePath(filePath);
            fileRepository.save(uploadedFile);

            return ResponseEntity.ok(file.getOriginalFilename() + filePath);
        }catch(IOException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to upload file: " + e.getMessage());
        }
    }

}
