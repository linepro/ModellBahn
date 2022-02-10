package com.linepro.modellbahn.util.impexp.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.dataformat.csv.CsvSchema.Column;
import com.fasterxml.jackson.dataformat.csv.CsvSchema.ColumnType;
import com.linepro.modellbahn.controller.impl.ApiNames;
import com.linepro.modellbahn.model.DecoderFunktionModel;
import com.linepro.modellbahn.util.impexp.impl.CsvSchemaGenerator.CandidateColumn;

public class CsvSchemaGeneratorTest {

    private static final Class<DecoderFunktionModel> MODEL_CLASS = DecoderFunktionModel.class;

    private static final CandidateColumn DECODER_ID = new CandidateColumn(ApiNames.DECODER_ID, ColumnType.STRING);
    private static final CandidateColumn FUNKTION = new CandidateColumn(ApiNames.FUNKTION, ColumnType.STRING);
    private static final CandidateColumn BEZEICHNUNG = new CandidateColumn(ApiNames.BEZEICHNUNG, ColumnType.STRING);
    private static final CandidateColumn PROGRAMMABLE = new CandidateColumn(ApiNames.PROGRAMMABLE, ColumnType.BOOLEAN);
    private static final CandidateColumn DELETED = new CandidateColumn(ApiNames.DELETED, ColumnType.BOOLEAN);

    private static final List<CandidateColumn> CANDIDATES = Arrays.asList(DECODER_ID, FUNKTION, BEZEICHNUNG, PROGRAMMABLE, DELETED);

    private static final List<String> HEADER = Arrays.asList(ApiNames.DECODER_ID, ApiNames.FUNKTION, ApiNames.ADRESS, ApiNames.BEZEICHNUNG, ApiNames.PROGRAMMABLE, ApiNames.DELETED);

    private static final Column CSV_DECODER_ID = new Column(0, ApiNames.DECODER_ID, ColumnType.STRING);
    private static final Column CSV_FUNKTION = new Column(1, ApiNames.FUNKTION, ColumnType.STRING);
    private static final Column CSV_BEZEICHNUNG = new Column(2, ApiNames.BEZEICHNUNG, ColumnType.STRING);
    private static final Column CSV_PROGRAMMABLE = new Column(3, ApiNames.PROGRAMMABLE, ColumnType.BOOLEAN);
    private static final Column CSV_DELETED = new Column(4, ApiNames.DELETED, ColumnType.BOOLEAN);

    private CsvSchemaGenerator generator;

    
    @BeforeEach
    void setUp() throws Exception {
        generator = new CsvSchemaGenerator();
    }

    @Test
    void getSchema() {
        CsvSchema schema = generator.getSchema(MODEL_CLASS);

        assertEqualsReflection(CSV_DECODER_ID, schema.column(0));
        assertEqualsReflection(CSV_FUNKTION, schema.column(1));
        assertEqualsReflection(CSV_BEZEICHNUNG, schema.column(2));
        assertEqualsReflection(CSV_PROGRAMMABLE, schema.column(3));
        assertEqualsReflection(CSV_DELETED, schema.column(4));
    }

    @Test
    void getSchemaHeader() {
        CsvSchema schema = generator.getSchema(MODEL_CLASS, HEADER);

        assertEqualsReflection(CSV_DECODER_ID, schema.column(0));
        assertEqualsReflection(CSV_FUNKTION, schema.column(1));
        assertEqualsReflection(new Column(2, ApiNames.ADRESS, ColumnType.STRING), schema.column(2));
        assertEqualsReflection(new Column(3, ApiNames.BEZEICHNUNG, ColumnType.STRING), schema.column(3));
        assertEqualsReflection(new Column(4, ApiNames.PROGRAMMABLE, ColumnType.BOOLEAN), schema.column(4));
        assertEqualsReflection(new Column(5, ApiNames.DELETED, ColumnType.BOOLEAN), schema.column(5));
    }

    @Test
    void getSchemaCandidates() {
        CsvSchema schema = generator.getSchema(CANDIDATES);

        assertEqualsReflection(CSV_DECODER_ID, schema.column(0));
        assertEqualsReflection(CSV_FUNKTION, schema.column(1));
        assertEqualsReflection(CSV_BEZEICHNUNG, schema.column(2));
        assertEqualsReflection(CSV_PROGRAMMABLE, schema.column(3));
        assertEqualsReflection(CSV_DELETED, schema.column(4));
    }

    @Test
    void getCandidates() throws Exception {
        List<CandidateColumn> columns = generator.getCandidates(MODEL_CLASS);

        assertEquals(CANDIDATES, columns);
    }

    @Test
    void getCandidate() throws Exception {
        Field field = MODEL_CLASS.getDeclaredField("deleted");

        CandidateColumn column = generator.getCandidate(field );

        assertEquals(DELETED, column);
    }

    void assertEqualsReflection(Object expected, Object actual) {
        if (!EqualsBuilder.reflectionEquals(expected, actual, false, null, true, "_arrayElementSeparator", "_next")) {
            assertEquals(
                ToStringBuilder.reflectionToString(expected, ToStringStyle.SIMPLE_STYLE, false),
                ToStringBuilder.reflectionToString(actual, ToStringStyle.SIMPLE_STYLE, false)
                );
        }
    }
}
