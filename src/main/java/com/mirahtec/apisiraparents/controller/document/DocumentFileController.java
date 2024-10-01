package com.mirahtec.apisiraparents.controller.document;

import jakarta.servlet.ServletContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaTypeFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@RestController
@RequestMapping("Documents")
@Slf4j
public class DocumentFileController {

    @Autowired
    private ServletContext context;

    // Root directory for the documents, adjusted to match your requirement
    private final Path rootLocation = Paths.get("src/main/resources/Documents/");

    @GetMapping("Etudiants/{matricule}/{file_name}")
    public ResponseEntity<?> downloadFile(@PathVariable String matricule, @PathVariable String file_name) {
        if (!StringUtils.hasText(matricule) || !StringUtils.hasText(file_name)) {
            return ResponseEntity.badRequest().body("Matricule or file name cannot be null or empty");
        }
        // Resolve the path for the requested file
        Path file = rootLocation.resolve("Etudiants").resolve(matricule).resolve(file_name);
        // Check if the file exists and is readable
        if (!Files.exists(file) || !Files.isReadable(file)) {
            return ResponseEntity.status(404).body("File not found or not readable");
        }
        try {
            // Read the file bytes
            byte[] fileBytes = Files.readAllBytes(file);

            // Determine the content type using MediaTypeFactory
            Optional<MediaType> mediaType = MediaTypeFactory.getMediaType(file_name);
            MediaType contentType = mediaType.orElse(MediaType.APPLICATION_OCTET_STREAM);

            // Return the file content with the appropriate media type
            return ResponseEntity.ok().contentType(contentType).body(fileBytes);
        } catch (IOException e) {
            log.error("An error occurred while fetching the document: {}", e.getMessage(), e);
            return ResponseEntity.status(500).body("An error occurred while fetching the document");
        }
    }
}
