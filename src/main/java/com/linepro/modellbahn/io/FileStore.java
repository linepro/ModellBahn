package com.linepro.modellbahn.io;

import java.nio.file.Path;

public interface FileStore {
    Path getItemPath(String modelName, String...identifiers);

    void removeItem(String modelName, String...identifiers);

    Path getFilePath(String modelName, String fieldName, String fileType, String...identifiers);

    void removeFile(Path filePath);
}
