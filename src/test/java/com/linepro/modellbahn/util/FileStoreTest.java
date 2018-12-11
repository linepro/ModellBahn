package com.linepro.modellbahn.util;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FileStoreTest {

    public static final String MODELL_BAHN = "https://localhost/ModellBahn:8086";

    public static final URI BASE_URI = URI.create(MODELL_BAHN);

    public static final String ENTITY_TYPE = "entityType";

    public static final String[] IDS = { "A", "B" };

    public static final String FILE_NAME = "file";

    public static final String FILE_TYPE = "jpg";

    public static final String TEST_FOLDER = "store/entityType/A/B";

    public static final String TEST_FILE = TEST_FOLDER + "/file.jpg";

    protected FileStore fileStore;

    protected Path root;

    @BeforeMethod
    public void setUp() throws Exception {
        root = Paths.get( System.getProperty("java.io.tmpdir"), "FileStoreTest", Long.toString(new Date().getTime()));

        fileStore = new FileStore();
        fileStore.setBaseUri(URI.create(MODELL_BAHN));
        fileStore.setStoreRoot(root.toString());
    }

    @AfterMethod
    public void tearDown() throws Exception {
        FileUtils.deleteDirectory(root.toFile());
    }

    @Test
    public void testSetBaseUri() {
        fileStore.setBaseUri(BASE_URI);
    }

    @Test
    public void testGetItemPath() {
        assertEquals(fileStore.getItemPath(ENTITY_TYPE, IDS), root.resolve(TEST_FOLDER));
    }

    @Test
    public void testRemoveItem() throws Exception {
        Path itemPath = fileStore.getItemPath(ENTITY_TYPE, IDS);

        Files.createDirectories(itemPath);

        fileStore.removeItem(ENTITY_TYPE, IDS);
    }

    @Test
    public void testGetFilePath() {
        assertEquals(fileStore.getFilePath(ENTITY_TYPE, IDS, FILE_NAME, FILE_TYPE), root.resolve(TEST_FILE));
    }

    @Test
    public void testRemoveFile() throws Exception {
        Path filePath = fileStore.getFilePath(ENTITY_TYPE, IDS, FILE_NAME, FILE_TYPE);
        Path itemPath = fileStore.getItemPath(ENTITY_TYPE, IDS);

        Files.createDirectories(itemPath);
        Files.createFile(filePath);

        fileStore.removeFile(ENTITY_TYPE, IDS, FILE_NAME, FILE_TYPE);

        assertFalse(Files.exists(filePath));
    }

    @Test
    public void testUrlForPath() throws Exception {
        assertEquals(fileStore.urlForPath(fileStore.getFilePath(ENTITY_TYPE, IDS, FILE_NAME, FILE_TYPE)), new URI(MODELL_BAHN + "/" + TEST_FILE));
    }
}