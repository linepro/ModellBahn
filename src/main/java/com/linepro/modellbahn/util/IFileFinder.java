package com.linepro.modellbahn.util;

/**
 * The Interface IFileFinder.
 */
import java.util.Set;
import java.io.File;
import java.util.Collection;

/**
 * The Interface IFileFinder.
 */
public interface IFileFinder {

    /**
     * Adds the paths.
     *
     * @param paths the paths
     */
    void addPaths(Collection<String> paths);

    /**
     * Adds the path.
     *
     * @param path the path
     */
    void addPath(String path);

    /**
     * Gets the paths.
     *
     * @return the paths
     */
    Set<String> getPaths();

    /**
     * Find file.
     *
     * @param path the path
     * @return the file
     */
    File findFile(String path);
}
