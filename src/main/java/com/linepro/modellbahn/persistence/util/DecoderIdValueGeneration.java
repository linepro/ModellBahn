package com.linepro.modellbahn.persistence.util;

import org.hibernate.tuple.AnnotationValueGeneration;
import org.hibernate.tuple.GenerationTiming;
import org.hibernate.tuple.ValueGenerator;

public class DecoderIdValueGeneration implements AnnotationValueGeneration<DecoderId> {

    private static final long serialVersionUID = 5172658914712355667L;

    private final DecoderIdGenerator generator;
    
    public DecoderIdValueGeneration() {
        this.generator = new DecoderIdGenerator();
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
    public void initialize(DecoderId annotation, Class<?> propertyType) {
    }
}
