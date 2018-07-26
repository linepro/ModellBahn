package com.linepro.modellbahn.util;

/**
 * StaticContentFinder.
 * A static to bridge Guice / CDI because I can't get the official bridge to work
 */
public class StaticContentFinder {

    /** The Constant fileFinder. */
    protected static final IFileFinder fileFinder = new FileFinder();

    /**
     * Gets the file finder.
     *
     * @return the file finder
     */
    public static IFileFinder get() {
        return fileFinder;
    }
}