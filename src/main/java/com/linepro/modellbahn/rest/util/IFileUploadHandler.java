package com.linepro.modellbahn.rest.util;

import java.io.InputStream;
import java.nio.file.Path;
import java.util.Map;

import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.media.multipart.ContentDisposition;

public interface IFileUploadHandler {
    Path upload(String entityType, String[] entityIds, ContentDisposition fileDetail, InputStream fileData, Map<String, MediaType> accepted) throws Exception;
}
