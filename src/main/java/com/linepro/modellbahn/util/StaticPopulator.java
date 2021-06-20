package com.linepro.modellbahn.util;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.controller.impl.AcceptableMediaTypes;
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

    private static final String DATA_STORE = "/static/data/";

    private static final String DATA_STORE_BUILD_FOLDER = "classpath:" + DATA_STORE + "**/*";

    @Autowired
    private final FileStore fileStore;

    @Autowired
    private final FileUploadHandler fileHandler;

    @PostConstruct
    void populate() {
        try {
            Set<String> acceptable = Stream.concat(
                            AcceptableMediaTypes.DOCUMENT_TYPES.stream(), 
                            AcceptableMediaTypes.IMAGE_TYPES.stream()
                            )
                            .map(t -> t.getSubtype())
                            .collect(Collectors.toSet());

            ResourcePatternResolver patternResolver = new PathMatchingResourcePatternResolver();   
            Stream.of(patternResolver.getResources(DATA_STORE_BUILD_FOLDER))
                                     .forEach(r -> {
                                         try {
                                             if (r.contentLength() > 0) {
                                                 String uri = r.getURI().toString();
                                                 String[] parts = uri.substring(uri.indexOf(DATA_STORE)+DATA_STORE.length()).split("/");

                                                 String modelName = parts[0];
                                                 String fileName = parts[parts.length-1];

                                                 int extIndex = fileName.lastIndexOf(".");
                                                 String extension = extIndex > -1 ? fileName.substring(extIndex+1).toLowerCase() : null;

                                                 if (extension != null && acceptable.contains(extension)) {
                                                     String fieldName = fileName.endsWith(".pdf") ?
                                                                            fileName.contains("explo") ? ApiNames.EXPLOSIONSZEICHNUNG : ApiNames.ANLEITUNGEN :
                                                                            fileName.contains("_xl") ? ApiNames.GROSSANSICHT : ApiNames.ABBILDUNG;

                                                     String identifiers[] = ArrayUtils.subarray(parts, 1, parts.length-1);

                                                     Path filePath = fileStore.getFilePath(modelName, fieldName, extension, identifiers);

                                                     if (!filePath.toFile().exists()) {
                                                         fileHandler.saveFile(r.getInputStream(), fileName, modelName, fieldName, identifiers);
                                                     }
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
