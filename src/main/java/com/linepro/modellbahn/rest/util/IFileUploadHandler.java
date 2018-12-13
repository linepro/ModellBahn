package com.linepro.modellbahn.rest.util;

import java.io.InputStream;
import java.nio.file.Path;
import java.util.List;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

public interface IFileUploadHandler {

    boolean isAcceptable(FormDataContentDisposition fileDetail, InputStream fileData, List<String> extensions) throws Exception;

    Path upload(String entityType, String[] entityIds, FormDataContentDisposition fileDetail, InputStream fileData) throws Exception;
}
