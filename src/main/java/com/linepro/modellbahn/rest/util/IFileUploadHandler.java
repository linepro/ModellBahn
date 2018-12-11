package com.linepro.modellbahn.rest.util;

import java.io.InputStream;
import java.nio.file.Path;

import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

public interface IFileUploadHandler {

    Path upload(String entityType, String[] entityIds, String fieldName, FormDataContentDisposition fileDetail,
            InputStream fileData, MediaType mediaType) throws Exception;

}
