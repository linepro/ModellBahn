package com.linepro.modellbahn.model.keys;

import javax.persistence.Query;

import com.linepro.modellbahn.model.IDecoderTyp;
import com.linepro.modellbahn.persistence.DBNames;
import com.linepro.modellbahn.rest.util.ApiNames;
import com.linepro.modellbahn.util.ToStringBuilder;

public class DecoderTypCVKey extends BaseKey {

    protected final IDecoderTyp decoderTyp;

    protected final Integer cv;

    public DecoderTypCVKey(final IDecoderTyp decoderTyp, final Integer cv) {
        this.decoderTyp = decoderTyp;
        this.cv = cv;
    }

    public IDecoderTyp getDecoderTyp() {
        return decoderTyp;
    }

    public Integer getCv() {
        return cv;
    }

    @Override
    public void addCriteria(Query query) {
        query.setParameter(DBNames.DECODER_TYP, getDecoderTyp());
        query.setParameter(DBNames.CV, getCv());
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append(ApiNames.DECODER_TYP, getDecoderTyp())
                .append(ApiNames.CV, getCv())
                .toString();
    }
}
