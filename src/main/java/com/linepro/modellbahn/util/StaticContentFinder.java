package com.linepro.modellbahn.util;

/**
 * StaticContentFinder.
 */
public class StaticContentFinder {

    /** The Constant fileFinder. */
    private static final IFileFinder fileFinder = new FileFinder();

    private static final IFileStore fileStore = new FileStore();

    private StaticContentFinder() {
    }

    /**
     * Gets the file finder.
     *
     * @return the file finder
     */
    public static IFileFinder getFinder() {
        return fileFinder;
    }

    public static IFileStore getStore() { return fileStore; }
}