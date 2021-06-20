/*
 * 
 */
package com.linepro.modellbahn.io;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

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

import com.linepro.modellbahn.controller.impl.ApiMessages;
import com.linepro.modellbahn.util.exceptions.ModellBahnException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * The Class FileUpload
 */
@Slf4j
@Component(PREFIX + "FileUploadHandlerImpl")
@RequiredArgsConstructor
public class FileUploadHandlerImpl implements FileUploadHandler {

    /** The file store. */
    @Autowired
    private final FileStore fileStore;

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
    public Path upload(MultipartFile multipart, String modelName, String fieldName, String...identifiers) {
        try (InputStream in = multipart.getInputStream()) {
            return saveFile(in, multipart.getOriginalFilename(), modelName, fieldName, identifiers);
        } catch (Exception e) {
            throw ModellBahnException.raise(ApiMessages.FILE_ERROR, e)
                                     .addValue(multipart.getOriginalFilename());
        }
    }

    @Override
    public Path saveFile(InputStream inputStream, String originalFilename, String modelName, String fieldName, String...identifiers) {
        String pathname = fileStore.itemPath(modelName, identifiers).toString();

        new File(pathname).mkdirs();

        String fileName = originalFilename;
        String extension = null;

        int extensionStart = fileName.lastIndexOf('.');

        if (extensionStart >= 0) {
            extension = fileName.substring(extensionStart+1).toLowerCase();
            fileName = fileName.substring(0, extensionStart);
        }

        Path filePath = fileStore.getFilePath(modelName, fieldName, extension, identifiers);

        writeToFile(filePath, inputStream, originalFilename);

        log.info("File {} uploaded to {}", originalFilename, filePath);

        return filePath;
    }

    /**
     * Write to file.
     * @param filePath 
     * @param fileName the file name
     * @param fileDetail the file detail
     * @param fileData the file data
     */
    private void writeToFile(Path filePath, InputStream inputStream, String originalFilename) {

        try (OutputStream out = new FileOutputStream(filePath.toFile(), false)) {
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
            throw ModellBahnException.raise(ApiMessages.FILE_ERROR, e)
                                     .addValue(originalFilename);
        }
    }
}