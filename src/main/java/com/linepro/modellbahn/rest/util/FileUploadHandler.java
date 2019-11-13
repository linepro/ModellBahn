/*
 * 
 */
package com.linepro.modellbahn.rest.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.util.Collection;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import com.linepro.modellbahn.util.IFileStore;
import com.linepro.modellbahn.util.StaticContentFinder;

/**
 * The Class FileUpload
 */
public class FileUploadHandler implements IFileUploadHandler {

    /** The file store. */
    private final IFileStore fileStore;

    /** The logger. */
    private final Logger logger;

    /**
     * Instantiates a new file upload
     */
    @Inject
    public FileUploadHandler() {
        this.fileStore = StaticContentFinder.getStore();
        this.logger = LoggerFactory.getILoggerFactory().getLogger(getClass().getName());
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
    public Path upload(String entityType, String[] entityIds, MultipartFile multipart) throws Exception {
        String pathname = fileStore.getItemPath(entityType, entityIds).toString();

        new File(pathname).mkdirs();

        String fileName = multipart.getOriginalFilename();
        String extension = null;

        int extensionStart = fileName.lastIndexOf('.');

        if (extensionStart >= 0) {
            extension = fileName.substring(extensionStart+1).toLowerCase();
            fileName = fileName.substring(0, extensionStart);
        }

        Path filePath = fileStore.getFilePath(entityType, entityIds, fileName, extension);

        writeToFile(multipart);

        logger.info("File {} uploaded to {}", multipart.getOriginalFilename(), filePath);

        return filePath;
    }

    /**
     * Write to file.
     * @param fileName the file name
     * @param fileDetail the file detail
     * @param fileData the file data
     * @throws Exception the exception
     */
    private void writeToFile(MultipartFile multipart) throws Exception {
        int read;
        byte[] buffer = new byte[1024];

        OutputStream out = new FileOutputStream(multipart.getOriginalFilename(), false);

        while ((read = multipart.getInputStream().read(buffer)) != -1) {
            out.write(buffer, 0, read);
        }

        out.flush();
        out.close();
    }
}
