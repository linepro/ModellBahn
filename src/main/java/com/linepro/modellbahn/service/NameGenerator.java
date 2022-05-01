package com.linepro.modellbahn.service;

public interface NameGenerator {

    /**
     * generates a name for the description
     * @param description the description
     * @return the generated name
     */
    String generate(String description);

}
