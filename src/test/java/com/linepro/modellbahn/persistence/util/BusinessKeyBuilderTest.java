package com.linepro.modellbahn.persistence.util;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.List;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.linepro.modellbahn.model.impl.Achsfolg;

public class BusinessKeyBuilderTest {

    protected BusinessKeyBuilder builder;

    @BeforeMethod
    public void setUp() throws Exception {
        builder = new BusinessKeyBuilder();
    }

    @Test
    public void testBuild() throws Exception {
        List<String> keys = builder.build(Achsfolg.class);
        
        assertEquals(keys.size(), 1);
        assertTrue(keys.contains("name"));
    }

}
