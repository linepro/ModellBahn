package com.linepro.modellbahn.model.keys;

import javax.persistence.Query;

import com.linepro.modellbahn.model.IDecoderTyp;
import com.linepro.modellbahn.persistence.DBNames;
import com.linepro.modellbahn.rest.util.ApiNames;
import com.linepro.modellbahn.util.ToStringBuilder;

public class DecoderTypAdressKey extends BaseKey {

    protected final IDecoderTyp decoderTyp;

    protected final Integer index;

    public DecoderTypAdressKey(final IDecoderTyp decoderTyp, final Integer index) {
        this.decoderTyp = decoderTyp;
        this.index = index;
    }

    public IDecoderTyp getDecoderTyp() {
        return decoderTyp;
    }

    public Integer getCv() {
        return index;
    }

    @Override
    public void addCriteria(Query query) {
        query.setParameter(DBNames.DECODER_TYP, getDecoderTyp());
        query.setParameter(DBNames.INDEX, getCv());
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append(ApiNames.DECODER_TYP, getDecoderTyp())
                .append(ApiNames.INDEX, getCv())
                .toString();
    }
}
