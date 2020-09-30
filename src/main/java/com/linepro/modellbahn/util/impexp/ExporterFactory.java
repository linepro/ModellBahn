package com.linepro.modellbahn.util.impexp;

public interface ExporterFactory {

    Exporter getExporter(DataType type);
}
