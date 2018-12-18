package com.linepro.modellbahn.model.keys;

import javax.persistence.Query;

import com.linepro.modellbahn.model.IDecoder;
import com.linepro.modellbahn.persistence.DBNames;
import com.linepro.modellbahn.rest.util.ApiNames;
import com.linepro.modellbahn.util.ToStringBuilder;

public class DecoderCVKey extends BaseKey {

    private final IDecoder decoder;

    private final Integer cv;

    public DecoderCVKey(final IDecoder decoder, final Integer cv) {
        this.decoder = decoder;
        this.cv = cv;
    }

    private IDecoder getDecoder() {
        return decoder;
    }

    private Integer getCv() {
        return cv;
    }

    @Override
    public void addCriteria(Query query) {
        query.setParameter(DBNames.DECODER, getDecoder());
        query.setParameter(DBNames.CV, getCv());
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append(ApiNames.DECODER, getDecoder())
                .append(ApiNames.CV, getCv())
                .toString();
    }
}
