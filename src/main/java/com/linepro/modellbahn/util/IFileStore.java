package com.linepro.modellbahn.util;

import java.net.URI;
import java.nio.file.Path;

public interface IFileStore {
    void setBaseUri(URI baseUri);

    void setStoreRoot(String fileStoreRoot);

    Path getItemPath(String entityType, String[] entityIds);

    void removeItem(String entityType, String[] entityIds);

    Path getFilePath(String entityType, String[] entityIds, String fieldName, String fileType);

    void removeFile(String entityType, String[] entityIds, String fieldName, String fileType);

    URI urlForPath(Path file);
}
