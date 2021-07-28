package com.linepro.modellbahn.io;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;
import static org.webjars.WebJarAssetLocator.WEBJARS_PATH_PREFIX;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.webjars.WebJarAssetLocator;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service(PREFIX + "ResourceEndpoints")
@RequiredArgsConstructor
public class ResourceEndpoints {

    public static final String WEBJARS_LOCATION = "classpath:" + WEBJARS_PATH_PREFIX + "/";

    @Value("#{'${spring.resources.static-locations:classpath:/static/,classpath:/public/,classpath:/resources/}'.split(',')}")
    private List<String> locations;

    @Value("${springdoc.swagger-ui.path:/swagger-ui/index.html}")
    @Getter
    private String swaggerUi;

    @Getter
    private String swaggerResources;

    @Value("${com.linepro.modellbahn.homePage:/modellbahn-ui/index.html}")
    @Getter
    private String homePage;

    @Autowired
    private final FileStore fileStore;

    @Getter
    private Map<String,List<String>> endPoints;

    public String getHomePageRedirect() {
        return "redirect:" + homePage;
    }

    public String getSwaggerUiRedirect() {
        return "redirect:" + swaggerUi;
    }

    @PostConstruct
    public void initialize() {
        endPoints = new TreeMap<>(
            new WebJarAssetLocator().getWebJars()
                                    .entrySet()
                                    .stream()
                                    .collect(
                                        Collectors.toMap(
                                            e -> pathPattern(e.getKey()), 
                                            e -> Collections.singletonList(WEBJARS_LOCATION + e.getKey() + Optional.ofNullable(e.getValue()).map(v -> "/" + v).orElse("") +  "/")
                                        )
                                    )
                                );

        List<String> staticFolders = new ArrayList<>();
        staticFolders.add("file:" + FilenameUtils.separatorsToUnix(fileStore.getStoreFolder().replace("/data", "")) + "/");
        staticFolders.addAll(locations);

        endPoints.put(fileStore.getStaticPathPattern(), staticFolders);

        log.info("Resource endpoints: {}", endPoints);

        swaggerResources = swaggerUi.replace("/index.html", "/**");
    }

    protected String pathPattern(String root) {
        return "/" + root + "/**";
    }
}
