package com.linepro.modellbahn.controller.base;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    Path updateFile(List<MediaType> mediaTypes, MultipartFile multipart, String modelName, String fieldName, String...identifiers) throws IllegalArgumentException, IOException;

    void deleteFile(Path file) throws IOException;

}
