package com.linepro.modellbahn.io;

import java.nio.file.Path;

public interface FileStore {

    String getStaticPathPattern();
    
    String getStorePath();

    String getStoreFolder();

    Path itemPath(String modelName, String...identifiers);

    String fileUrl(Path source);

    Path getFilePath(String modelName, String fieldName, String fileType, String...identifiers);

    void removeFile(Path filePath);
}
