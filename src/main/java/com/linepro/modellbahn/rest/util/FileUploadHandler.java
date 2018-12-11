/*
 * 
 */
package com.linepro.modellbahn.rest.util;

import static com.linepro.modellbahn.rest.util.AcceptableMediaTypes.BMP_TYPE;
import static com.linepro.modellbahn.rest.util.AcceptableMediaTypes.GIF_TYPE;
import static com.linepro.modellbahn.rest.util.AcceptableMediaTypes.JPG_TYPE;
import static com.linepro.modellbahn.rest.util.AcceptableMediaTypes.PDF_TYPE;
import static com.linepro.modellbahn.rest.util.AcceptableMediaTypes.PNG_TYPE;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.AbstractMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.inject.Inject;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;

import com.linepro.modellbahn.util.IFileStore;

/**
 * The Class FileUploadHandler.
 */
public class FileUploadHandler implements IFileUploadHandler {

    public Map<MediaType, String> MEDIA_TYPES = Stream.of(
            new AbstractMap.SimpleEntry<>(BMP_TYPE, ".bmp"),
            new AbstractMap.SimpleEntry<>(GIF_TYPE, ".gif"),
            new AbstractMap.SimpleEntry<>(JPG_TYPE, ".jpg"),
            new AbstractMap.SimpleEntry<>(PNG_TYPE, ".png"),
            new AbstractMap.SimpleEntry<>(PDF_TYPE, ".pdf"),
            new AbstractMap.SimpleEntry<>(MediaType.TEXT_PLAIN_TYPE, ".txt"))
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

    /** The file store. */
    private final IFileStore fileStore;

    /** The logger. */
    private final Logger logger;

    /**
     * Instantiates a new file upload handler.
     * @param fileStore the file store
     * @param logManger the log manger
     */
    @Inject
    public FileUploadHandler(IFileStore fileStore, ILoggerFactory logManger) {
        this.fileStore = fileStore;
        this.logger = logManger.getLogger(getClass().getName());
    }
   
	/**
     * Upload.
     * @param entityType the entity type
     * @param entityIds the entity ids
     * @param fieldName the field name
     * @param fileDetail the file detail
     * @param fileData the file data
     * @param fileType the file type
     * @return the path
     */
    @Override
	public Path upload(String entityType, String[] entityIds, String fieldName,
	        FormDataContentDisposition fileDetail, InputStream fileData, MediaType mediaType) throws Exception {
        Files.createDirectory(fileStore.getItemPath(entityType, entityIds));
        
		String fileType = getExtension(mediaType);
		
        Path filePath = fileStore.getFilePath(entityType, entityIds, fieldName, fileType);

        writeToFile(filePath, fileDetail, fileData);
		
		logger.info("File " + fileDetail + " uploaded to " + filePath);
        
        return filePath;
	}

    protected String getExtension(MediaType mediaType) throws Exception {
        String extension = MEDIA_TYPES.get(mediaType);
        
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
