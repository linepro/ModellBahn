package com.linepro.modellbahn.io;

import java.io.File;
import java.util.Set;

/**
 * The Interface IFileFinder.
 */
public interface FileFinder {

    /**
     * Find file.
     *
     * @param path the path
     * @return the file
     */
    File findFile(String path);

    Set<String> getAbsolutePaths();
}
