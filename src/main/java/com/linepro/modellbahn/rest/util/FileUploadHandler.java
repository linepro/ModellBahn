/*
 * 
 */
package com.linepro.modellbahn.rest.util;

import com.fasterxml.jackson.annotation.JsonView;
import com.linepro.modellbahn.model.IArtikel;
import com.linepro.modellbahn.model.impl.Artikel;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.util.IFileStore;
import com.linepro.modellbahn.util.StaticContentFinder;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

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
    public boolean isAcceptable(FormDataContentDisposition fileDetail, InputStream fileData, List<String> extensions) throws Exception {
        if (fileDetail != null && fileData != null) {
            for (String extension : extensions) {
                if (fileDetail.getFileName().endsWith(extension)) {
                    return true;
                }
            }
        }
        return false;
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
	public Path upload(String entityType, String[] entityIds, FormDataContentDisposition fileDetail, InputStream fileData) throws Exception {
        Files.createDirectory(fileStore.getItemPath(entityType, entityIds));

        String fileName = fileDetail.getFileName();
        String extension = getExtension(fileDetail.getType());
		
        Path filePath = fileStore.getFilePath(entityType, entityIds, fileName, extension);

        writeToFile(filePath, fileDetail, fileData);
		
		logger.info("File " + fileDetail + " uploaded to " + filePath);
        
        return filePath;
	}

    protected String getExtension(String mediaType) throws Exception {
        String extension = AcceptableMediaTypes.EXTENTSIONS.get(mediaType);
        
        if (extension == null) {
            throw new IllegalArgumentException("Unsupported mediaType " + mediaType);
        }

        return extension;
    }

	/**
     * Write to file.
     * @param fileName the file name
     * @param fileDetail the file detail
     * @param fileData the file data
     * @throws Exception the exception
     */
    protected void writeToFile(Path fileName, FormDataContentDisposition fileDetail, InputStream fileData) throws Exception {
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
