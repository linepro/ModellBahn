package com.linepro.modellbahn.util.impexp;

public interface ImporterFactory {

    Importer getImporter(DataType type);
}
