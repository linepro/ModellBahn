package com.linepro.modellbahn.util;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

public class SelectorTest {

    private static final String NAME = "name";
    
    private static final String SETTER = "setter";
    
    private static final String GETTER = "getter";
    
    private static final int HASH_CODE = 3374336;
    
    private static final String TO_STRING = "Selector[name=" + NAME + ",getter=" + GETTER + ",setter=" + SETTER + ",collection=false]";

    private Selector selector;
    
    private Method getter;
    
    private Method setter;

    private String value = "";

    protected String getter() {
        return value;
    }

    protected void setter(String value) {
        this.value = value;
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
        assertNotEquals(selector, null);
        assertNotEquals(new Object(), selector);
        assertEquals(selector, selector);
        assertEquals(new Selector(NAME, String.class, setter, getter, false), selector);
        assertNotEquals(new Selector(NAME + "!", String.class, setter, getter, true), selector);
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
