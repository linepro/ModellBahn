package com.linepro.modellbahn.util;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.lang.reflect.Method;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SelectorTest {

    private static final String NAME = "name";
    
    private static final String SETTER = "setter";
    
    private static final String GETTER = "getter";
    
    private static final int HASH_CODE = 3374336;
    
    private static final String TO_STRING = "Selector[name=" + NAME + ",getter=" + GETTER + ",setter=" + SETTER + ",collection=false]";

    private Selector selector;
    
    private Method getter;
    
    private Method setter;

    protected String getter() {
        return "";
    }

    protected void setter(String value) {
    }

    @BeforeMethod
    public void setUp() throws Exception {
        getter = getClass().getDeclaredMethod(GETTER);
        setter = getClass().getDeclaredMethod(SETTER, String.class);
        
        selector = new Selector(NAME, String.class, getter, setter, false);
    }

    @Test
    public void testGetName() {
        assertEquals(selector.getName(), NAME);
    }

    @Test
    public void testGetGetter() {
        assertEquals(selector.getGetter(), getter);
    }

    @Test
    public void testGetSetter() {
        assertEquals(selector.getSetter(), setter);
    }

    @Test
    public void testEquals() {
        assertFalse(selector.equals(null));
        assertFalse(selector.equals(new Object()));
        assertTrue(selector.equals(selector));
        assertTrue(selector.equals(new Selector(NAME, String.class, setter, getter, false)));
        assertFalse(selector.equals(new Selector(NAME+"!", String.class, setter, getter, true)));
    }

    @Test
    public void testHashCode() {
        assertEquals(selector.hashCode(), HASH_CODE);
    }

    @Test
    public void testToString() {
        assertEquals(selector.toString(), TO_STRING);
    }

}
