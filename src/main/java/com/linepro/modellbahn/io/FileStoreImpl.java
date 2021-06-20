package com.linepro.modellbahn.io;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Component(PREFIX + "FileStoreImpl")
public class FileStoreImpl implements FileStore {

    protected static final String FILE = "file:";
    protected static final String STATIC_FILE_FOLDER = "static";
    protected static final String STATIC_FILE_LOCATIONS = FILE + STATIC_FILE_FOLDER;
    protected static final String WILDCARD = "/**";
    protected static final String STATIC_FILE_PATH = WILDCARD;

    @Value("${spring.mvc.static-path-pattern:" + STATIC_FILE_PATH + "}")
    private String staticPathPattern;

    @Value("${com.linepro.modellbahn.fileStore:" + STATIC_FILE_LOCATIONS + "}")
    private List<String> staticLocations;

    @Value("${spring.mvc.servlet.context-path:}")
    private String servletPath;
    
    private String storePath;

    private String storeFolder;

    public FileStoreImpl() {
    }

    @PostConstruct
    public void initialize() {
        storePath   = staticPathPattern.replace(WILDCARD, "");
        storeFolder = staticLocations.stream()
                                     .filter(s -> s.startsWith(FILE))
                                     .findFirst()
                                     .map(s -> s.replace(FILE, ""))
                                     .orElse(STATIC_FILE_FOLDER);
    }

    @Override
    public Path itemPath(String modelName, String... identifiers) {
        return Paths.get(storeFolder, "data").resolve(Paths.get(modelName, identifiers));
    }

    @Override
    public String fileUrl(Path source) {
        String filePath = FilenameUtils.separatorsToUnix(Paths.get(storeFolder).relativize(source).toString());

        return ServletUriComponentsBuilder.fromCurrentContextPath().path(servletPath + "/" + storePath + "/").path(filePath).toUriString();
    }

    @Override
    public Path getFilePath(String modelName, String fieldName, String fileType, String... identifiers) {
        Path itemPath = itemPath(modelName, identifiers);

        return itemPath.resolve(String.join(".", fieldName, fileType));
    }

    @Override
    public void removeFile(Path filePath) {
        try {
            FileUtils.forceDelete(filePath.toFile());
        } catch (FileNotFoundException e) {
            // That's ok. We didn't like it anyway.
        } catch (Exception e) {
            // Should we throw it up?
            log.error("Failed to delete file {}: {}", filePath.toString(), e);
        }
    }
}
