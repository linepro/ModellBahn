package com.linepro.modellbahn.rest.util;

import java.nio.file.Path;
import java.util.Collection;

import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

public interface IFileUploadHandler {
    boolean isAcceptable(MultipartFile multipart, Collection<MediaType> accepted);

    Path upload(String entityType, String[] entityIds, MultipartFile multipart) throws Exception;
}
