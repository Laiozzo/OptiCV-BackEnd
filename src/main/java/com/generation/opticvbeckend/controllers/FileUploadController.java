package com.generation.opticvbeckend.controllers;

import com.generation.opticvbeckend.model.entities.UploadedFile;
import com.generation.opticvbeckend.model.parse.CVParserIText;
import com.generation.opticvbeckend.model.repositories.UploadedFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/files")
@CrossOrigin(origins = "http://localhost:4200")
public class FileUploadController {

    @Value("${file.upload-dir:C:/uploads/}") // Imposta una directory predefinita per il caricamento dei file
    private String fileUploadDir;

    @Autowired
    private CVParserIText cvParserIText; // Parser per estrarre testo dai PDF

    @Autowired
    private UploadedFileRepository fileRepository; // Repository per salvare i dettagli del file nel database

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            // Verifica che il file sia un PDF
            if (!file.getContentType().equals("application/pdf")) {
                return ResponseEntity.badRequest().body("Per favore, carica un file PDF valido.");
            }

            // Nome originale del file
            String fileName = file.getOriginalFilename();

            // Percorso completo per salvare temporaneamente il file
            File destinationFile = new File(Paths.get(fileUploadDir, fileName).toString());

            // Crea la directory se non esiste
            if (!destinationFile.getParentFile().exists()) {
                destinationFile.getParentFile().mkdirs();
            }

            // Salva il file temporaneamente
            file.transferTo(destinationFile);

            // Usa il parser per estrarre il contenuto del file PDF
            String parsedText = cvParserIText.pdfToString(destinationFile.getAbsolutePath());

            // Salva i dettagli del file nel database
            UploadedFile uploadedFile = new UploadedFile();
            uploadedFile.setFileName(fileName);
            uploadedFile.setFilePath(destinationFile.getAbsolutePath());
            uploadedFile.setParsedContent(parsedText); // Campo per il testo estratto (deve essere aggiunto nella tua entità)
            uploadedFile.setUser(Request);
            fileRepository.save(uploadedFile);

            // (Opzionale) Elimina il file temporaneo
            destinationFile.delete();

            // Restituisci il testo estratto come risposta
            return ResponseEntity.ok(parsedText);

        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Errore durante il caricamento del file.");
        }
    }
}
