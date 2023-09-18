package com.am.reaprich.reaprichbackend.util.io;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileUploader {
    public static String SaveFile(String fileName, Path directory, MultipartFile multipartFile)
            throws IOException {
        if (!Files.exists(directory)) {
            Files.createDirectories(directory);
        }

        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = directory.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            return filePath.toString();
        } catch (IOException ioe) {
            throw new IOException("Could not save file: " + fileName, ioe);
        }
    }
}
