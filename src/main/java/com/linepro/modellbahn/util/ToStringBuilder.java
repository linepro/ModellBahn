package com.linepro.modellbahn.util;

import java.io.File;

import org.apache.commons.lang3.builder.ToStringStyle;

import com.linepro.modellbahn.model.refs.IDecoderTypRef;
import com.linepro.modellbahn.model.refs.INamedItemRef;
import com.linepro.modellbahn.model.refs.IProduktRef;

/**
 * TODO: by proxy??
 * @author jgoff
 *
 */
public class ToStringBuilder extends org.apache.commons.lang3.builder.ToStringBuilder {

    public ToStringBuilder(Object object) {
        super(object, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public ToStringBuilder(Object object, ToStringStyle style) {
        super(object, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public ToStringBuilder(Object object, ToStringStyle style, StringBuffer buffer) {
        super(object, ToStringStyle.SHORT_PREFIX_STYLE, buffer);
    }

    @Override
    public ToStringBuilder append(boolean value) {
        super.append(value);
        return this;
    }

    @Override
    public ToStringBuilder append(boolean[] array) {
        super.append(array);
        return this;
    }

    @Override
    public ToStringBuilder append(byte value) {
        super.append(value);
        return this;
    }

    @Override
    public ToStringBuilder append(byte[] array) {
        super.append(array);
        return this;
    }

    @Override
    public ToStringBuilder append(char value) {
        super.append(value);
        return this;
    }

    @Override
    public ToStringBuilder append(char[] array) {
        super.append(array);
        return this;
    }

    @Override
    public ToStringBuilder append(double value) {
        super.append(value);
        return this;
    }

    @Override
    public ToStringBuilder append(double[] array) {
        super.append(array);
        return this;
    }

    @Override
    public ToStringBuilder append(float value) {
        super.append(value);
        return this;
    }

    @Override
    public ToStringBuilder append(float[] array) {
        super.append(array);
        return this;
    }

    @Override
    public ToStringBuilder append(int value) {
        super.append(value);
        return this;
    }

    @Override
    public ToStringBuilder append(int[] array) {
        super.append(array);
        return this;
    }

    @Override
    public ToStringBuilder append(long value) {
        super.append(value);
        return this;
    }

    @Override
    public ToStringBuilder append(long[] array) {
        super.append(array);
        return this;
    }

    @Override
    public ToStringBuilder append(Object obj) {
        super.append(obj);
        return this;
    }

    @Override
    public ToStringBuilder append(Object[] array) {
        super.append(array);
        return this;
    }

    @Override
    public ToStringBuilder append(short value) {
        super.append(value);
        return this;
    }

    @Override
    public ToStringBuilder append(short[] array) {
        super.append(array);
        return this;
    }

    @Override
    public ToStringBuilder append(String fieldName, boolean value) {
        super.append(fieldName, value);
        return this;
    }

    @Override
    public ToStringBuilder append(String fieldName, boolean[] array) {
        super.append(fieldName, array);
        return this;
    }

    @Override
    public ToStringBuilder append(String fieldName, boolean[] array, boolean fullDetail) {
        super.append(fieldName, array, fullDetail);
        return this;
    }

    @Override
    public ToStringBuilder append(String fieldName, byte value) {
        super.append(fieldName, value);
        return this;
    }

    @Override
    public ToStringBuilder append(String fieldName, byte[] array) {
        super.append(fieldName, array);
        return this;
    }

    @Override
    public ToStringBuilder append(String fieldName, byte[] array, boolean fullDetail) {
        super.append(fieldName, array, fullDetail);
        return this;
    }

    @Override
    public ToStringBuilder append(String fieldName, char value) {
        super.append(fieldName, value);
        return this;
    }

    @Override
    public ToStringBuilder append(String fieldName, char[] array) {
        super.append(fieldName, array);
        return this;
    }

    @Override
    public ToStringBuilder append(String fieldName, char[] array, boolean fullDetail) {
        super.append(fieldName, array, fullDetail);
        return this;
    }

    @Override
    public ToStringBuilder append(String fieldName, double value) {
        super.append(fieldName, value);
        return this;
    }

    @Override
    public ToStringBuilder append(String fieldName, double[] array) {
        super.append(fieldName, array);
        return this;
    }

    @Override
    public ToStringBuilder append(String fieldName, double[] array, boolean fullDetail) {
        super.append(fieldName, array, fullDetail);
        return this;
    }

    @Override
    public ToStringBuilder append(String fieldName, float value) {
        super.append(fieldName, value);
        return this;
    }

    @Override
    public ToStringBuilder append(String fieldName, float[] array) {
        super.append(fieldName, array);
        return this;
    }

    @Override
    public ToStringBuilder append(String fieldName, float[] array, boolean fullDetail) {
        super.append(fieldName, array, fullDetail);
        return this;
    }

    @Override
    public ToStringBuilder append(String fieldName, int value) {
        super.append(fieldName, value);
        return this;
    }

    @Override
    public ToStringBuilder append(String fieldName, int[] array) {
        super.append(fieldName, array);
        return this;
    }

    @Override
    public ToStringBuilder append(String fieldName, int[] array, boolean fullDetail) {
        super.append(fieldName, array, fullDetail);
        return this;
    }

    @Override
    public ToStringBuilder append(String fieldName, long value) {
        super.append(fieldName, value);
        return this;
    }

    @Override
    public ToStringBuilder append(String fieldName, long[] array) {
        super.append(fieldName, array);
        return this;
    }

    @Override
    public ToStringBuilder append(String fieldName, long[] array, boolean fullDetail) {
        super.append(fieldName, array, fullDetail);
        return this;
    }

    @Override
    public ToStringBuilder append(String fieldName, Object obj) {
        super.append(fieldName, obj);
        return this;
    }

    @Override
    public ToStringBuilder append(String fieldName, Object obj, boolean fullDetail) {
        super.append(fieldName, obj, fullDetail);
        return this;
    }

    @Override
    public ToStringBuilder append(String fieldName, Object[] array) {
        super.append(fieldName, array);
        return this;
    }

    @Override
    public ToStringBuilder append(String fieldName, Object[] array, boolean fullDetail) {
        super.append(fieldName, array, fullDetail);
        return this;
    }

    @Override
    public ToStringBuilder append(String fieldName, short value) {
        super.append(fieldName, value);
        return this;
    }

    @Override
    public ToStringBuilder append(String fieldName, short[] array) {
        super.append(fieldName, array);
        return this;
    }

    @Override
    public ToStringBuilder append(String fieldName, short[] array, boolean fullDetail) {
        super.append(fieldName, array, fullDetail);
        return this;
    }

    @Override
    public ToStringBuilder appendAsObjectToString(Object srcObject) {
        super.appendAsObjectToString(srcObject);
        return this;
    }

    @Override
    public ToStringBuilder appendSuper(String superToString) {
        super.appendSuper(superToString);
        return this;
    }

    @Override
    public ToStringBuilder appendToString(String toString) {
        super.appendToString(toString);
        return this;
    }

    public ToStringBuilder append(String fieldName, IDecoderTypRef value) {
        super.append(fieldName, value != null ?  new Object[] { value.getHersteller(), value.getName() } : null);
        return this;
    }

    public ToStringBuilder append(String fieldName, INamedItemRef value) {
        super.append(fieldName, value != null ? value.getName() : null);
        return this;
    }

    public ToStringBuilder append(String fieldName, IProduktRef value) {
        super.append(fieldName, value != null ? new Object[] { value.getHersteller(), value.getName() } : null);
        return this;
    }

    public ToStringBuilder append(String fieldName, File value) {
        super.append(fieldName, value != null ? value.getAbsolutePath() : null);
        return this;
    }

    @Override
    public String build() {
        return super.build();
    }
}
