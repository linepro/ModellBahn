package com.linepro.modellbahn.rest.util;

import java.io.InputStream;
import java.nio.file.Path;
import java.util.Collection;

import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.media.multipart.ContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;

public interface IFileUploadHandler {
    boolean isAcceptable(FormDataBodyPart body, Collection<MediaType> accepted);

    Path upload(String entityType, String[] entityIds, ContentDisposition fileDetail, InputStream fileData) throws Exception;
}
