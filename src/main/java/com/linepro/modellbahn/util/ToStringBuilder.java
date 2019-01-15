package com.linepro.modellbahn.util;

import java.io.File;

import org.apache.commons.lang3.builder.ToStringStyle;

import com.linepro.modellbahn.model.IArtikel;
import com.linepro.modellbahn.model.IDecoder;
import com.linepro.modellbahn.model.IDecoderTyp;
import com.linepro.modellbahn.model.IProdukt;
import com.linepro.modellbahn.model.IProduktTeil;
import com.linepro.modellbahn.model.IUnterKategorie;
import com.linepro.modellbahn.model.IZugConsist;
import com.linepro.modellbahn.model.refs.INamedItemRef;

/**
 * TODO: by proxy??
 * @author jgoff
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

    public ToStringBuilder append(String fieldName, IArtikel value) {
        super.append(fieldName, value != null ? new Object[] {value.getArtikelId(), value.getBezeichnung()} : null);
        return this;
    }

    public ToStringBuilder append(String fieldName, IDecoder value) {
        super.append(fieldName, value != null ? new Object[] {value.getDecoderId(), value.getBezeichnung()} : null);
        return this;
    }

    public ToStringBuilder append(String fieldName, INamedItemRef value) {
        super.append(fieldName, value != null ? new Object[] {value.getName(), value.getBezeichnung()} : null);
        return this;
    }

    public ToStringBuilder append(String fieldName, IDecoderTyp value) {
        super.append(fieldName, value != null ? new Object[] {value.getHersteller(), value.getBestellNr()} : null);
        return this;
    }

    public ToStringBuilder append(String fieldName, IProdukt value) {
        super.append(fieldName, value != null ? new Object[] {value.getHersteller(), value.getBestellNr()} : null);
        return this;
    }

    public ToStringBuilder append(String fieldName, IProduktTeil value) {
        super.append(fieldName, value != null ? new Object[] { value.getTeil(), value.getAnzahl()} : null);
        return this;
    }

    public ToStringBuilder append(String fieldName, IUnterKategorie value) {
        super.append(fieldName, value != null ? new Object[] {value.getName(), value.getBezeichnung()} : null);
        return this;
    }

    public ToStringBuilder append(String fieldName, IZugConsist value) {
        super.append(fieldName, value != null ? new Object[] {value.getPosition(), value.getArtikel()} : null);
        return this;
    }

    public ToStringBuilder append(String fieldName, File value) {
        super.append(fieldName, value != null ? value.getAbsolutePath() : null);
        return this;
    }
}
