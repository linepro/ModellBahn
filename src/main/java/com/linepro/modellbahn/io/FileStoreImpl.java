package com.linepro.modellbahn.io;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Component(PREFIX + "FileStoreImpl")
public class FileStoreImpl implements FileStore {

    @Value("${com.linepro.modellbahn.filestore.root:}")
    private String fileRoot;

    @Value("${com.linepro.modellbahn.filestore.name:static}")
    private String storeFolder;

    public FileStoreImpl() {
    }

    @Override
    public Path fileStoreRoot() {
         return Paths.get(fileRoot, storeFolder);
    }

    @Override
    public Path getItemPath(String modelName, String...identifiers) {
        return fileStoreRoot().resolve(Paths.get(modelName, identifiers));
   }

    @Override
    public void removeItem(String modelName, String...identifiers) {
        Path itemPath = getItemPath(modelName, identifiers).toAbsolutePath();

        try {
            FileUtils.forceDelete(itemPath.toFile());
        } catch(FileNotFoundException e) {
            // That's ok. We didn't like it anyway.
        } catch(Exception e) {
            // Should we throw it up?
            log.error("Failed to delete folder {}: {}", itemPath.toString(), e);
        }
    }

    @Override
    public Path getFilePath(String modelName, String fieldName, String fileType, String...identifiers) {
        Path itemPath = getItemPath(modelName, identifiers);

        return itemPath.resolve(String.join(".", fieldName, fileType));
    }

    @Override
    public void removeFile(Path filePath) {
        try {
            FileUtils.forceDelete(filePath.toFile());
        } catch(FileNotFoundException e) {
            // That's ok. We didn't like it anyway.
        } catch(Exception e) {
            // Should we throw it up?
            log.error("Failed to delete file {}: {}", filePath.toString(), e);
        }
    }
}
