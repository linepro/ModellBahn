package com.linepro.modellbahn.io;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import java.nio.file.Path;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.linepro.modellbahn.controller.impl.ApiMessages;
import com.linepro.modellbahn.util.exceptions.ModellBahnException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component(PREFIX + "FileServiceImpl")
public class FileServiceImpl implements FileService {

    private final FileStore fileStore;

    private final FileUploadHandler handler;

    @Autowired
    public FileServiceImpl(FileStore fileStore, FileUploadHandler handler) {
        this.fileStore = fileStore;
        this.handler = handler;
    }

    @Override
    public Path updateFile(List<MediaType> mediaTypes, MultipartFile multipart, String modelType, String fieldName, String...identifiers) {
        if (!handler.isAcceptable(multipart, mediaTypes)) {
            throw new ModellBahnException(ApiMessages.INVALID_FILE).addValue(multipart.getOriginalFilename())
                                                                   .setStatus(HttpStatus.BAD_REQUEST);
        }

        try {
            return handler.upload(multipart, modelType, fieldName, identifiers);
        } catch (Exception e) {
            log.error("Failed uploading '{}': {}", multipart.getOriginalFilename(), e.getMessage(), e);
        }

        return null;
    }

    @Override
    public Path deleteFile(Path file) {
        try {
            if (file != null) {
                fileStore.removeFile(file);
            }

            return null;
        } catch (Exception e) {
            log.error("Failed to delete file {}: {}", file.toString(), e);
        }

        return file;
    }
}
