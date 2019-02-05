/*
 * 
 */
package com.linepro.modellbahn.rest.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.util.Collection;

import javax.inject.Inject;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.media.multipart.ContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    public boolean isAcceptable(FormDataBodyPart body, Collection<MediaType> accepted) {
        MediaType mimeType = body.getMediaType();
        
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
    public Path upload(String entityType, String[] entityIds, ContentDisposition fileDetail, InputStream fileData) throws Exception {
        String pathname = fileStore.getItemPath(entityType, entityIds).toString();

        new File(pathname).mkdirs();

        String fileName = fileDetail.getFileName();
        String extension = null;

        int extensionStart = fileName.lastIndexOf(".");

        if (extensionStart >= 0) {
            extension = fileName.substring(extensionStart+1).toLowerCase();
            fileName = fileName.substring(0, extensionStart);
        }

        Path filePath = fileStore.getFilePath(entityType, entityIds, fileName, extension);

        writeToFile(filePath, fileDetail, fileData);

        logger.info("File " + fileDetail + " uploaded to " + filePath);

        return filePath;
    }

    /**
     * Write to file.
     * @param fileName the file name
     * @param fileDetail the file detail
     * @param fileData the file data
     * @throws Exception the exception
     */
    private void writeToFile(Path fileName, ContentDisposition fileDetail, InputStream fileData) throws Exception {
        int read;
        byte[] buffer = new byte[1024];

        OutputStream out = new FileOutputStream(fileName.toFile(), false);

        while ((read = fileData.read(buffer)) != -1) {
            out.write(buffer, 0, read);
        }

        out.flush();
        out.close();
    }
}
