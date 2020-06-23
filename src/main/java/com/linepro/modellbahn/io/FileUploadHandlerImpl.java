/*
 * 
 */
package com.linepro.modellbahn.io;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

/**
 * The Class FileUpload
 */
@Component
@Slf4j
public class FileUploadHandlerImpl implements FileUploadHandler {

    /** The file store. */
    private final FileStore fileStore;

    /**
     * Instantiates a new file upload
     */
    @Autowired
    public FileUploadHandlerImpl(FileStore fileStore) {
        this.fileStore = fileStore;
    }

    @Override
    public boolean isAcceptable(MultipartFile multipart, Collection<MediaType> accepted) {
        MediaType mimeType = MediaType.valueOf(multipart.getContentType());
        
        return accepted.contains(mimeType);
    }

    /**
     * Upload.
     * @param entityType the entity type
     * @param entityIds the entity ids
     * @param fileDetail the file detail
     * @param fileData the file data
     * @return the path
     */
    @Override
    public Path upload(MultipartFile multipart, String modelName, String fieldName, String...identifiers) throws Exception {
        String pathname = fileStore.getItemPath(modelName, identifiers).toString();

        new File(pathname).mkdirs();

        String fileName = multipart.getOriginalFilename();
        String extension = null;

        int extensionStart = fileName.lastIndexOf('.');

        if (extensionStart >= 0) {
            extension = fileName.substring(extensionStart+1).toLowerCase();
            fileName = fileName.substring(0, extensionStart);
        }

        Path filePath = fileStore.getFilePath(modelName, fieldName, extension, identifiers);

        writeToFile(filePath, multipart);

        log.info("File {} uploaded to {}", multipart.getOriginalFilename(), filePath);

        return filePath;
    }

    /**
     * Write to file.
     * @param filePath 
     * @param fileName the file name
     * @param fileDetail the file detail
     * @param fileData the file data
     * @throws Exception the exception
     */
    private void writeToFile(Path filePath, MultipartFile multipart) throws Exception {

        String originalFilename = multipart.getOriginalFilename();

        try (
            OutputStream out = new FileOutputStream(filePath.toFile(), false);
            InputStream inputStream = multipart.getInputStream()) {

            int read = -1;
            byte[] buffer = new byte[1024*8];

            do {
                read = inputStream.read(buffer);

                if (read > 0) {
                    out.write(buffer, 0, read);
                }
            } while (read > 0);
    
            out.flush();
        } catch (Exception e) {
            log.error("Error uploading {}: {}", originalFilename, e.getMessage());
        }
    }
}