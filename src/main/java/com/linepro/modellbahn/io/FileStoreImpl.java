package com.linepro.modellbahn.io;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Getter
public class FileStoreImpl implements FileStore {

    @Value("${filestore.root:}")
    private String fileRoot;

    @Value("${filestore.name:static}")
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
        } catch(Exception e) {
            log.error("Failed to delete folder " + itemPath.toString(), e);
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
        } catch(Exception e) {
            log.error("Failed to delete file " + filePath.toString(), e);
        }
    }
}
