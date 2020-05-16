package com.linepro.modellbahn.io;

import java.nio.file.Path;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.linepro.modellbahn.controller.impl.ApiMessages;
import com.linepro.modellbahn.i18n.MessageTranslator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
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
    public Path updateFile(List<MediaType> mediaTypes, MultipartFile multipart, String modelType, String fieldName, String...identifiers) {
        try {
            if (!handler.isAcceptable(multipart, mediaTypes)) {
                throw new IllegalArgumentException(messageTranslator.getMessage(ApiMessages.INVALID_FILE, multipart.getOriginalFilename()));
            }

            return handler.upload(multipart, modelType, fieldName, identifiers);
        } catch (Exception e) {
            log.error(messageTranslator.getMessage(ApiMessages.INVALID_FILE, multipart.getOriginalFilename()));
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
            log.error(messageTranslator.getMessage(ApiMessages.INVALID_FILE, file.toString()));
        }
        
        return file;
    }
}
