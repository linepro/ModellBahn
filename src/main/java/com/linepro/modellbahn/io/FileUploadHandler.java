package com.linepro.modellbahn.io;

import java.nio.file.Path;
import java.util.Collection;

import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

public interface FileUploadHandler {
    boolean isAcceptable(MultipartFile multipart, Collection<MediaType> accepted);

    Path upload(MultipartFile multipart, String modelName, String fieldName, String...identifiers);
}
