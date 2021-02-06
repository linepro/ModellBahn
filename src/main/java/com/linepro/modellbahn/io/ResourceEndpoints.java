package com.linepro.modellbahn.io;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;
import static org.webjars.WebJarAssetLocator.WEBJARS_PATH_PREFIX;

import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.WebJarAssetLocator;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service(PREFIX+"ResourceEndpoints")
@RequiredArgsConstructor
public class ResourceEndpoints {

    @Autowired
    private final FileStore fileStore;

    public static final String WEBJARS_LOCATION = "classpath:" + WEBJARS_PATH_PREFIX + "/";

    @Getter
    private Map<String,String> endPoints;

    @PostConstruct
    public void initialize() {
        endPoints = new TreeMap<>(
            new WebJarAssetLocator().getWebJars()
                                    .entrySet()
                                    .stream()
                                    .collect(
                                        Collectors.toMap(
                                            e -> pathPattern(e.getKey()), 
                                            e -> WEBJARS_LOCATION + e.getKey() + Optional.ofNullable(e.getValue()).map(v -> "/" + v).orElse("") +  "/"
                                            )
                                        )
                                    );
        
        endPoints.put(fileStore.getStaticPathPattern(), "file:" + FilenameUtils.separatorsToUnix(fileStore.getStoreFolder()) +  "/");

        log.info("Resource endpoints: {}", endPoints);
    }

    protected String pathPattern(String root) {
        return "/" + root + "/**";
    }
}
