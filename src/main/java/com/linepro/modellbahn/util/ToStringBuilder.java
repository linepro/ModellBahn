package com.linepro.modellbahn.util;

import static com.linepro.modellbahn.persistence.util.ProxyUtils.isAvailable;

import org.apache.commons.lang3.builder.ToStringStyle;

import com.linepro.modellbahn.entity.Artikel;
import com.linepro.modellbahn.entity.Decoder;
import com.linepro.modellbahn.entity.DecoderCv;
import com.linepro.modellbahn.entity.DecoderFunktion;
import com.linepro.modellbahn.entity.DecoderTyp;
import com.linepro.modellbahn.entity.DecoderTypCv;
import com.linepro.modellbahn.entity.DecoderTypFunktion;
import com.linepro.modellbahn.entity.NamedItem;
import com.linepro.modellbahn.entity.Produkt;
import com.linepro.modellbahn.entity.ProduktTeil;
import com.linepro.modellbahn.entity.UnterKategorie;

public class ToStringBuilder extends org.apache.commons.lang3.builder.ToStringBuilder {

    public ToStringBuilder(Object object) {
        super(object, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public static Object[] summary(NamedItem named) {
        return isAvailable(named) ? new Object[] { named.getName(), named.getBezeichnung() } : null;
    }

    public static Object[] summary(Artikel artikel) {
        return isAvailable(artikel) ? new Object[] { artikel.getArtikelId(), artikel.getBezeichnung() } : null;
    }

    public static Object[] summary(Decoder decoder) {
        return isAvailable(decoder) ? new Object[] { decoder.getDecoderId(), decoder.getBezeichnung() } : null;
    }

    public static Object[] summary(DecoderCv cv) {
        return isAvailable(cv) ? new Object[] { summary(cv.getDecoder()), summary(cv.getCv()) } : null;
    }

    public static Object[] summary(DecoderFunktion funktion) {
        return isAvailable(funktion) ? new Object[] { summary(funktion.getDecoder()), summary(funktion.getFunktion()), funktion.getBezeichnung() } : null;
    }

    public static Object[] summary(DecoderTyp decoderTyp) {
        return isAvailable(decoderTyp) ? new Object[] { summary(decoderTyp.getHersteller()), decoderTyp.getBestellNr() } : null;
    }

    public static Object[] summary(DecoderTypCv cv) {
        return isAvailable(cv) ? new Object[] { summary(cv.getDecoderTyp()), cv.getCv(), cv.getBezeichnung() } : null;
    }

    public static Object[] summary(DecoderTypFunktion funktion) {
        return isAvailable(funktion) ? new Object[] { summary(funktion.getDecoderTyp()), funktion.getFunktion(), funktion.getBezeichnung() } : null;
    }

    public static Object[] summary(Produkt produkt) {
        return isAvailable(produkt) ? new Object[] { produkt.getHersteller().getName(), produkt.getBestellNr() } : null;
    }

    public static Object[] summary(ProduktTeil teil) {
        return isAvailable(teil) ? new Object[] { summary(teil.getProdukt()), summary(teil.getTeil()) } : null;
    }

    public static Object[] summary(UnterKategorie unterKategorie) {
        return isAvailable(unterKategorie) ? new Object[] { summary(unterKategorie.getKategorie()), unterKategorie.getName(), unterKategorie.getBezeichnung() } : null;
    }

}
