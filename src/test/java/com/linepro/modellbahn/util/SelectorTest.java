package com.linepro.modellbahn.util;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.lang.reflect.Method;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SelectorTest {

    protected static final String NAME = "name";
    
    protected static final String SETTER = "setter";
    
    protected static final String GETTER = "getter";
    
    protected static final int HASH_CODE = 3374336;
    
    protected static final String TO_STRING = "Selector[name=" + NAME + ",getter=" + GETTER + ",setter=" + SETTER + ",collection=false]";

    protected Selector selector;
    
    protected Method getter;
    
    protected Method setter;

    protected String getter() {
        return "";
    }

    protected void setter(String value) {
    }

    @BeforeMethod
    public void setUp() throws Exception {
        getter = getClass().getDeclaredMethod(GETTER);
        setter = getClass().getDeclaredMethod(SETTER, String.class);
        
        selector = new Selector(NAME, getter, setter, false);
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
        assertTrue(selector.equals(new Selector(NAME, setter, getter, false)));
        assertFalse(selector.equals(new Selector(NAME+"!", setter, getter, true)));
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
