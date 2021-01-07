package com.linepro.modellbahn.io;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;
import static org.webjars.WebJarAssetLocator.WEBJARS_PATH_PREFIX;

import java.util.AbstractMap.SimpleEntry;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.WebJarAssetLocator;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Service(PREFIX+"ResourceEndpoints")
@RequiredArgsConstructor
public class ResourceEndpoints {

    @Autowired
    private final FileStore fileStore;

    public static final Map<String,String> RESOURCE_ENDPOINTS = Stream.of(
                        new SimpleEntry<>("/static/**",  "file:static/"),
                        new SimpleEntry<>("/resources/**", "file:resources/")
                    )
                    .collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue()));

    public static final String WEBJARS_LOCATION = "classpath:" + WEBJARS_PATH_PREFIX + "/";

    @Getter
    private Map<String,String> endPoints;

    @PostConstruct
    public void initialize() {
        endPoints = new HashMap<>(new WebJarAssetLocator().getWebJars()
                                          .entrySet()
                                          .stream()
                                          .collect(
                                              Collectors.toMap(
                                                  e -> "/" + e.getKey() + "/**", 
                                                  e -> WEBJARS_LOCATION + e.getKey() + Optional.ofNullable(e.getValue()).map(v -> "/" + v).orElse("") +  "/"
                                                  )
                                              ));
        
        endPoints.putAll(RESOURCE_ENDPOINTS);

        endPoints.put("/", "file:" + FilenameUtils.separatorsToUnix(fileStore.fileStoreRoot().toString()));
    }
}
