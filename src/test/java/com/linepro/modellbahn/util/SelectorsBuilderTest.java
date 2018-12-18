package com.linepro.modellbahn.util;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.lang.reflect.Method;
import java.util.Map;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.JoinColumn;

import org.apache.commons.lang3.StringUtils;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SelectorsBuilderTest {

    class Base {
        public void setId(Integer id) {}

        Integer getId() { return 1; }

        @Basic
        String getName() { return "fred"; }
        public void setName(String name) {}
    }
    
    public class Annotated extends Base {
        @Column(name="name")
        public String getName() { return super.getName(); }
    }
    
    public class BigDecimalAnnotated extends Annotated {
        @JoinColumn(name="id")
        public Integer getId() { return super.getId(); }
    }
    
    private Object testObject;

    private SelectorsBuilder builder;

    private Selector idSelector;

    private Selector nameSelector;
    
    @BeforeMethod
    public void setUp() {
        builder = new SelectorsBuilder(); 
    }

    private Object setObject(Object testObject) throws Exception {
        idSelector = getSelector(testObject, "id", Integer.class);
        nameSelector = getSelector(testObject, "name", String.class);
        return testObject;
    }
    
    private Selector getSelector(Object testObject, String name, Class<?> type) throws Exception {
        Class<?> clazz = testObject.getClass();
        String capitalized = StringUtils.capitalize(name);
        Method getter = clazz.getMethod("get" + capitalized);
        Method setter = clazz.getMethod("set" + capitalized, type);
        return new Selector(name, getter.getReturnType(), getter, setter, false);
    }

    @Test
    public void testBuildBase() throws Exception {
        testObject = setObject(new Base());
        
        Map<String,Selector> selectors = builder.build(testObject.getClass(), Column.class);
        
        assertEquals(selectors.size(), 0);
    }

    @Test
    public void testBuildAnnotated() throws Exception {
        testObject = setObject(new Annotated());
        
        Map<String,Selector> selectors = builder.build(testObject.getClass(), Column.class);
        
        assertEquals(selectors.size(), 1);
        assertTrue(selectors.containsValue(nameSelector));
        assertTrue(selectors.containsKey(nameSelector.getName()));
    }

    @Test
    public void testBuildBigDecimalAnnotated() throws Exception {
        testObject = setObject(new BigDecimalAnnotated());
        
        Map<String,Selector> selectors = builder.build(testObject.getClass(), Column.class, JoinColumn.class);
        
        assertEquals(selectors.size(), 2);
        assertTrue(selectors.containsValue(idSelector));
        assertTrue(selectors.containsKey(idSelector.getName()));
        assertTrue(selectors.containsValue(nameSelector));
        assertTrue(selectors.containsKey(nameSelector.getName()));
    }
}
