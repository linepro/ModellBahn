package com.linepro.modellbahn.util;

/**
 * StaticContentFinder.
 * A static to bridge Guice / CDI because I can't get the official bridge to work
 */
public class StaticContentFinder {

    /** The Constant fileFinder. */
    protected static final IFileFinder fileFinder = new FileFinder();

    protected static final IFileStore fileStore = new FileStore();

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