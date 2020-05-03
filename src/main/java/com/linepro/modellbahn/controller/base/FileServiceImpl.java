package com.linepro.modellbahn.controller.base;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.linepro.modellbahn.util.FileStore;
import com.linepro.modellbahn.util.i18n.MessageTranslator;

@Component
public class FileServiceImpl implements FileService {

    private final FileStore fileStore;
    
    private final FileUploadHandler handler;

    private final MessageTranslator messageTranslator;
    
    @Autowired
    public FileServiceImpl(FileStore fileStore, FileUploadHandler handler, MessageTranslator messageTranslator) {
        this.fileStore = fileStore;
        this.handler = handler;
        this.messageTranslator = messageTranslator;
    }

    @Override
    public Path updateFile(List<MediaType> mediaTypes, MultipartFile multipart, String modelType, String fieldName, String...identifiers) throws IllegalArgumentException, IOException {
        try {
            if (!handler.isAcceptable(multipart, mediaTypes)) {
                throw new IllegalArgumentException(messageTranslator.getMessage(ApiMessages.INVALID_FILE, multipart.getOriginalFilename()));
            }

            return handler.upload(multipart, modelType, fieldName, identifiers);
        } catch (Exception e) {
            throw new IOException(messageTranslator.getMessage(ApiMessages.INVALID_FILE, multipart.getOriginalFilename()));
        }
    }

    @Override
    public void deleteFile(Path file) throws IOException {
        try {
            if (file != null) {
                fileStore.removeFile(file);
            }
        } catch (Exception e) {
            throw new IOException(messageTranslator.getMessage(ApiMessages.INVALID_FILE, file.toString()));
        }
    }
}
