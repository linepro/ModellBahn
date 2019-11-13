package com.linepro.modellbahn.persistence.util;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.hibernate.annotations.ValueGenerationType;

@ValueGenerationType(generatedBy = ArtikelIdValueGeneration.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface ArtikelId {
}
