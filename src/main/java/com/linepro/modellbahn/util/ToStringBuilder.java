package com.linepro.modellbahn.util;

import static com.linepro.modellbahn.persistence.util.ProxyUtils.isAvailable;

import java.util.Collection;

import org.apache.commons.lang3.builder.ToStringStyle;

import com.linepro.modellbahn.entity.Artikel;
import com.linepro.modellbahn.entity.Decoder;
import com.linepro.modellbahn.entity.DecoderTyp;
import com.linepro.modellbahn.entity.DecoderTypCv;
import com.linepro.modellbahn.entity.DecoderTypFunktion;
import com.linepro.modellbahn.entity.Item;
import com.linepro.modellbahn.entity.NamedItem;
import com.linepro.modellbahn.entity.Produkt;

/**
 * TODO: by proxy??
 * @author jgoff
 */
public class ToStringBuilder extends org.apache.commons.lang3.builder.ToStringBuilder {

    public ToStringBuilder(Object object) {
        super(object, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public ToStringBuilder append(String fieldName, NamedItem value) {
        super.append(fieldName, isAvailable(value) ? new Object[] { value.getName(), value.getBezeichnung() } : null);
        return this;
    }

    public ToStringBuilder append(String fieldName, Artikel value) {
        super.append(fieldName, isAvailable(value) ? new Object[] { value.getArtikelId(), value.getBezeichnung() } : null);
        return this;
    }

    public ToStringBuilder append(String fieldName, Decoder value) {
        super.append(fieldName, isAvailable(value) ? new Object[] { value.getDecoderId(), value.getBezeichnung() } : null);
        return this;
    }

    public ToStringBuilder append(String fieldName, DecoderTyp value) {
        super.append(fieldName, isAvailable(value) ? new Object[] { value.getHersteller().getName(), value.getBestellNr(), value.getBezeichnung() } : null);
        return this;
    }

    public ToStringBuilder append(String fieldName, DecoderTypCv value) {
        super.append(fieldName, isAvailable(value) ? new Object[] { value.getCv(), value.getBezeichnung() } : null);
        return this;
    }

    public ToStringBuilder append(String fieldName, DecoderTypFunktion value) {
        super.append(fieldName, isAvailable(value) ? new Object[] { value.getFunktion(), value.getBezeichnung() } : null);
        return this;
    }

    public ToStringBuilder append(String fieldName, Produkt value) {
        super.append(fieldName, isAvailable(value) ? new Object[] { value.getHersteller().getName(), value.getBestellNr() } : null);
        return this;
    }

    public ToStringBuilder append(String fieldName, Item value) {
        super.append(fieldName, isAvailable(value) ? value : null);
        return this;
    }

    public ToStringBuilder append(String fieldName, Collection<Item> value) {
        super.append(fieldName, isAvailable(value) ? value : null);
        return this;
    }
}
