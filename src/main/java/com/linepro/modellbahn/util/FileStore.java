package com.linepro.modellbahn.util;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class FileStore implements IFileStore {

    private static final String STORE_FOLDER = "store";

    private final Logger logger;

    private URI baseUri = null;

    private Path fileRoot = null;

    private Path fileStoreRoot = null;

    @Inject
    public FileStore() {
        this.logger = LoggerFactory.getLogger(getClass().getName());
    }

    @Override
    public void setBaseUri(URI baseUri) {
        this.baseUri = baseUri;
    }

    @Override
    public String getStoreRoot() {
        return fileStoreRoot.toString();
    }

    @Override
    public void setStoreRoot(String fileRoot) {
        this.fileRoot = Paths.get(fileRoot);

        this.fileStoreRoot = Paths.get(fileRoot, STORE_FOLDER);

        StaticContentFinder.getFinder().addPath(this.fileStoreRoot.toString());
    }

    @Override
    public Path getItemPath(String entityType, String[] entityIds) {
        Path itemPath = fileStoreRoot.resolve(entityType);

        for (String part : entityIds) {
            itemPath = itemPath.resolve(part);
        }

        return itemPath;
    }

    @Override
    public void removeItem(String entityType, String[] entityIds) {
        Path itemPath = getItemPath(entityType, entityIds).toAbsolutePath();

        try {
            FileUtils.forceDelete(itemPath.toFile());
        } catch(Exception e) {
            logger.error("Failed to delete folder " + itemPath.toString(), e);
        }
    }

    @Override
    public Path getFilePath(String entityType, String[] entityIds, String fieldName, String fileType) {
        Path itemPath = getItemPath(entityType, entityIds);

        return itemPath.resolve(String.join(".", fieldName, fileType));
    }

    @Override
    public void removeFile(Path filePath) {
        try {
            FileUtils.forceDelete(filePath.toFile());
        } catch(Exception e) {
            logger.error("Failed to delete file " + filePath.toString(), e);
        }
    }

    @Override
    public URI urlForPath(Path file) {
        List<String> parts = new ArrayList<>();

        StringBuilder template = new StringBuilder(baseUri.toString());

        int i = 0;
        for ( Path partial : fileRoot.relativize(file)) {
            parts.add(partial.toString());
            template.append("/{arg");
            template.append(i++);
            template.append("}");
        }

        return UriBuilder.fromUri(template.toString()).build(parts.toArray());
    }
}
