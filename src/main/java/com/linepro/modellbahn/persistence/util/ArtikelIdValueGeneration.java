package com.linepro.modellbahn.persistence.util;

import org.hibernate.tuple.AnnotationValueGeneration;
import org.hibernate.tuple.GenerationTiming;
import org.hibernate.tuple.ValueGenerator;

public class ArtikelIdValueGeneration implements AnnotationValueGeneration<ArtikelId> {

    private static final long serialVersionUID = 5172658914712355667L;

    private final ArtikelIdGenerator generator;
    
    public ArtikelIdValueGeneration() {
        this.generator = new ArtikelIdGenerator();
    }
    
    @Override
    public GenerationTiming getGenerationTiming() {
        return GenerationTiming.INSERT;
    }

    @Override
    public ValueGenerator<?> getValueGenerator() {
        return generator;
    }

    @Override
    public boolean referenceColumnInSql() {
        return false;
    }

    @Override
    public String getDatabaseGeneratedReferencedColumnValue() {
        return null;
    }

    @Override
    public void initialize(ArtikelId annotation, Class<?> propertyType) {
    }
}
