/**
 * 
 */
package com.linepro.modellbahn.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.linepro.modellbahn.persistence.DBNames;

/**
 * @author JohnG
 */
public class NameGeneratorImplTest {

    private static final String NEGATED = "[^A-Z0-9\\-\\.]";

    private static final String MARKLIN_DESCRIPTION = "Märklin";

    private static final String MARKLIN_NAME = "MARKLIN";

    private static final String SNCB_DESCRIPTION = "Société Nationale des Chemins de Fer Belges (SNCB/NMBS)";

    private static final String SNCB_NAME = "SNCB";

    private static final String ACHSFOLG_DESCRIPTION = "1’E1’ h3 2’3’ T 28";

    private static final String ACHSFOLG_NAME = "1-E1-H3-2-3-T-28";

    private NameGeneratorImpl generator;

    @BeforeEach
    protected void setUp() throws Exception {
        generator = new NameGeneratorImpl();
    }

    @Test
    public void testGenerateDescription() {
        assertEquals(MARKLIN_NAME, generator.generate(MARKLIN_DESCRIPTION));
    }

    @Test
    public void testGenerateSNCB() {
        assertEquals(SNCB_NAME, generator.generate(SNCB_DESCRIPTION));
    }

    @Test
    public void testGenerateAchsfolg() {
        assertEquals(ACHSFOLG_NAME, generator.generate(ACHSFOLG_DESCRIPTION));
    }

    @Test
    public void testNegate() {
        assertEquals(NEGATED, generator.negate(DBNames.NAME_PATTERN));
    }

}
