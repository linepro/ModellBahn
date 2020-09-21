package com.linepro.modellbahn.util;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;

import java.io.IOException;
import java.nio.file.Path;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.controller.impl.ApiNames;
import com.linepro.modellbahn.io.FileStore;
import com.linepro.modellbahn.io.FileUploadHandler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.logstash.logback.encoder.org.apache.commons.lang3.ArrayUtils;

@Slf4j
@Component(PREFIX + "StaticPopulator")
@RequiredArgsConstructor
public class StaticPopulator {

    private static final String FOLDER_START = "static/";

    @Autowired
    private final FileStore fileStore;

    @Autowired
    private final FileUploadHandler fileHandler;

    @PostConstruct
    void populate() {
        try {
            ResourcePatternResolver patternResolver = new PathMatchingResourcePatternResolver();   
            Stream.of(patternResolver.getResources("classpath:" + FOLDER_START + "**/*"))
                                     .forEach(r -> {
                                         try {
                                             if (r.contentLength() > 0) {
                                                 String uri = r.getURI().toString();
                                                 String[] parts = uri.substring(uri.indexOf(FOLDER_START)).split("/");

                                                 String modelName = parts[1];
                                                 String fileName = parts[parts.length-1];

                                                 String fieldName = fileName.endsWith(".pdf") ?
                                                                        fileName.contains("explo") ? ApiNames.EXPLOSIONSZEICHNUNG : ApiNames.ANLEITUNGEN :
                                                                        fileName.contains("_xl") ? ApiNames.GROSSANSICHT : ApiNames.ABBILDUNG;

                                                 String identifiers[] = ArrayUtils.subarray(parts, 2, parts.length-1);
                                                 
                                                 String extension = null;

                                                 int extensionStart = fileName.lastIndexOf('.');

                                                 if (extensionStart >= 0) {
                                                     extension = fileName.substring(extensionStart+1).toLowerCase();
                                                 }

                                                 Path filePath = fileStore.getFilePath(modelName, fieldName, extension, identifiers);

                                                 if (!filePath.toFile().exists()) {
                                                     fileHandler.saveFile(r.getInputStream(), fileName, modelName, fieldName, identifiers);
                                                 }
                                             }
                                         } catch (IOException e) {
                                         }
                                     });
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }
}
