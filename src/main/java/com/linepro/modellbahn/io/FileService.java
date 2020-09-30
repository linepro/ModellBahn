package com.linepro.modellbahn.io;

import java.nio.file.Path;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    Path updateFile(List<MediaType> mediaTypes, MultipartFile multipart, String modelName, String fieldName, String...identifiers);

    Path deleteFile(Path file);

}
